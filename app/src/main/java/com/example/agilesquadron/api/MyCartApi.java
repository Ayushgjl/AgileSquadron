package com.example.agilesquadron.api;

import com.example.agilesquadron.model.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyCartApi {
    @GET("mycart")
    Call<List<Cart>> getMyCart();

    @GET("mycart")
    Call<Cart> getImage(@Header("Authorization") String id);

    @POST("mycart")
    Call<Void> addtoitem (@Body Cart cart);
}
