package com.cookandroid.login2;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InitMyapi {
    @FormUrlEncoded
    @POST("api/token/")
    Call<LoginResponse> getLoginResponse(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("accounts/register/")
    Call<SignupResponse> getSignupResponse(@Field("username") String username, @Field("email") String email, @Field("password1") String password1, @Field("password2") String password2);
}
