package com.example.agilesquadron.api;

import com.example.agilesquadron.model.Clothe;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CApi {

    @GET("clothe")
    Call<List<Clothe>> getClothe();


    @Multipart
    @POST("upload")
    Call<Clothe> uploadImage(@Part MultipartBody.Part img);

    @GET("clothe")
    Call<Clothe> getImage(@Header("Authorization") String id);
}
