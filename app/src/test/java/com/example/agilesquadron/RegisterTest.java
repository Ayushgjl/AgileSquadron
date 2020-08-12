package com.example.agilesquadron;

import com.example.agilesquadron.api.UserApi;
import com.example.agilesquadron.model.User;
import com.example.agilesquadron.serverresponse.SignUpResponse;
import com.example.agilesquadron.ui.url.Url;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;

public class RegisterTest {

    boolean expected = true;
    boolean actual = false;

    @Test
    public void signupTest() {
        com.example.agilesquadron.model.User user = new User("ayush", "gajurel", "9844435087", "ayushgjl0213", "ayushgjl0213", null);
        UserApi userAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> signUpResponseCall = userAPI.registerUser(user);
        try {
            Response<SignUpResponse> register = signUpResponseCall.execute();
            if (register.isSuccessful() && register.body().getStatus().equals("Signup Success")) {
                actual = false;
            }
            else {
                actual= true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(actual, expected);
    }

    @Test
    public void signupTestfailed() {
        com.example.agilesquadron.model.User user = new User("ayush", "gajurel", "9844435087", "ayushgjl0213", "ayushgjl0213", null);
        UserApi userAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> signUpResponseCall = userAPI.registerUser(user);
        try {
            Response<SignUpResponse> register = signUpResponseCall.execute();
            if (register.isSuccessful() && register.body().getStatus().equals("Signup Success")) {
                actual = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(actual, expected);

    }
}
