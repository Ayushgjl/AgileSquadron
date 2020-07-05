package com.example.agilesquadron.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesquadron.R;
import com.example.agilesquadron.api.UserApi;
import com.example.agilesquadron.model.User;
import com.example.agilesquadron.serverresponse.ImageResponse;
import com.example.agilesquadron.serverresponse.SignUpResponse;
import com.example.agilesquadron.strictmode.StrictModeClass;
import com.example.agilesquadron.ui.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView tvLogin;
    ImageView imgProfile;
    TextView etfirstname, etlastname, etUsername, etPhoneNumber, etPassword, etRePassword;
    Button btnregister;
    String imagePath;
    private String imageName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        tvLogin = findViewById(R.id.tvLogin);
        etfirstname = findViewById(R.id.etfirstname);
        etlastname = findViewById(R.id.etlastname);
        etUsername = findViewById(R.id.etUsername);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etpassword);
        etRePassword = findViewById(R.id.etrepasword);
        imgProfile = findViewById(R.id.imgProfile);
        btnregister = findViewById(R.id.btnregister);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals(etRePassword.getText().toString())) {
                    saveImageOnly();
                    signUp();
                } else {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                    etPassword.requestFocus();
                    return;
                }
            }
            private void signUp() {
                String firstname=etfirstname.getText().toString();
                String lastname=etlastname.getText().toString();
                String PhoneNumber=etPhoneNumber.getText().toString();
                String username =etUsername.getText().toString();
                String password=etPassword.getText().toString();

                User users=new User(firstname,lastname,PhoneNumber,username,password,imageName);

                UserApi userAPI= Url.getInstance().create(UserApi.class);
                Call<SignUpResponse> signUpCall=userAPI.registerUser(users);
                signUpCall.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(RegisterActivity.this, "Registered User", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            private void saveImageOnly() {
                File file=new File(imagePath);
                RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                MultipartBody.Part body=MultipartBody.Part.createFormData("myFile",
                        file.getName(),requestBody);
                UserApi userAPI= Url.getInstance().create(UserApi.class);
                Call<ImageResponse> responseBodyCall=userAPI.uploadImage(body);
                StrictModeClass.StrictMode();
                try {
                    Response<ImageResponse> imageResponseResponse=responseBodyCall.execute();
                    imageName=imageResponseResponse.body().getFilename();
                    Toast.makeText(RegisterActivity.this, "Image inserted", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(RegisterActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        });
    }
    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/");
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
}
