package com.example.agilesquadron.api;

import com.example.agilesquadron.model.CartDel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CartApi {

    @GET("mycart")
    Call<List<CartDel>> getCart();

    @Multipart
    @POST("upload")
    Call<CartDel> uploadImage(@Part MultipartBody.Part img);

    @GET("mycart")
    Call<CartDel> getImage(@Header("Authorization") String id);

    @DELETE("mycart/{xyz}")
    Call<CartDel> deleteItems(@Header("Authorization") String token, @Path("xyz") String _id);
}
