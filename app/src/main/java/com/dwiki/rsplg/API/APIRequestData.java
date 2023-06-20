package com.dwiki.rsplg.API;

import com.dwiki.rsplg.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("telepon") String telepon,
            @Field("foto") String foto,
            @Field("koordinat") String koordinat
    );

}
