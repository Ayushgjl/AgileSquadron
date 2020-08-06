package com.example.agilesquadron.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesquadron.R;
import com.example.agilesquadron.api.MyCartApi;
import com.example.agilesquadron.model.Cart;
import com.example.agilesquadron.ui.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClotheCartActivity extends AppCompatActivity {

    ImageView AddC;
    TextView tvClot,tvAddQuantity,tvAddPrice;
    Button btnAddClot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_cart);

        AddC=findViewById(R.id.AddC);
        tvClot=findViewById(R.id.tvClot);
        tvAddQuantity=findViewById(R.id.tvAddQuantity);
        tvAddPrice=findViewById(R.id.tvAddPrice);
        btnAddClot=findViewById(R.id.btnAddClot);

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            String imagename=bundle.getString("image");
            final String imgPath="http://10.0.2.2:3001/uploads/" + imagename;

            try{
                URL url = new URL(imgPath);
                AddC.setImageBitmap((BitmapFactory.decodeStream((InputStream) url.getContent())));
            }
            catch (Exception e){
                e.printStackTrace();
            }

            tvClot.setText(bundle.getString("name"));
            tvAddQuantity.setText(bundle.getString("quantity"));
            tvAddPrice.setText(bundle.getString("price"));
        }

        btnAddClot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ClotheCartActivity.this);
                builder.setMessage("Add this to cart?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CAddToCart();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }

    private void CAddToCart(){
        MyCartApi myCartApi= Url.getInstance().create(MyCartApi.class);
        String user_name="reshika";
        String p_name=tvClot.getText().toString();
        String p_quantity=tvAddQuantity.getText().toString();
        String p_price=tvAddPrice.getText().toString();

        Cart cart= new Cart(user_name,p_name,p_quantity,p_price);

        Call<Void> voidCall=myCartApi.addtoitem(cart);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ClotheCartActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ClotheCartActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();

            }
        });
    }
}