package com.example.agilesquadron.api;

import com.example.agilesquadron.model.Food;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FoodApi {
    @GET("food")
    Call<List<Food>> getFood();

    @Multipart
    @POST("upload")
    Call<Food> uploadImage(@Part MultipartBody.Part img);

    @GET("food")
    Call<Food> getImage(@Header("Authorization") String id);
}
