package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("affecter_echelons")
    Call<List<Echelon>> getEch();
    @POST("permissions")
    Call<Permission> createPermission(@Body Permission permission);
    @POST("demandes")
    Call<Document> createDoc(@Body Document document);
}
