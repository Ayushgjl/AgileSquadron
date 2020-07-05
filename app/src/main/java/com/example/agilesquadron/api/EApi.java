package com.example.agilesquadron.api;

import com.example.agilesquadron.model.Electronic;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface EApi {
    @GET("electronics")
    Call<List<Electronic>> getElectronic();

    @Multipart
    @POST("upload")
    Call<Electronic> uploadImage(@Part MultipartBody.Part img);

    @GET("electronics")
    Call<Electronic> getImage(@Header("Authorization") String id);
}
