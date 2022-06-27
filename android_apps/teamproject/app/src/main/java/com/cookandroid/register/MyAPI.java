package com.cookandroid.register;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyAPI {

    @Multipart
    @POST("/posts/")
    Call<ResponseBody> post_posts(
            @Part("title") RequestBody param,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("/accounts/")
    Call<ResponseBody> post_accounts(
            @Part("identify") RequestBody param1,
            @Part("password") RequestBody param2
    );

    @Multipart
    @POST("/check/")
    Call<ResponseBody> post_check(
            @Part("checkID") RequestBody param1,
            @Part("checkPW") RequestBody param2
    );
}
