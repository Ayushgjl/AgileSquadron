package com.example.agilesquadron.api;

import com.example.agilesquadron.model.Gift;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GApi {
    @GET("gift")
    Call<List<Gift>> getGift();


    @Multipart
    @POST("upload")
    Call<Gift> uploadImage(@Part MultipartBody.Part img);

    @GET("gift")
    Call<Gift> getImage(@Header("Authorization") String id);
}
