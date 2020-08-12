package com.example.agilesquadron;

import com.example.agilesquadron.api.UserApi;
import com.example.agilesquadron.model.username;
import com.example.agilesquadron.serverresponse.SignUpResponse;
import com.example.agilesquadron.ui.url.Url;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginTest {
    boolean actual = false;
    boolean expected= true;

    @Test
    public void Login(){
        com.example.agilesquadron.model.username Username = new username("arjudutta","arjudutta");
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> usersCall = userApi.checklogin(Username);

        try{
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getStatus().equals("Login Success")) {
                actual=false;
            }
            else {
                actual=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
