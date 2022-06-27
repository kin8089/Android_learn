package com.cookandroid.test_login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("users")
    @Headers({"Accept:application/json", "Content-Type:application/json", "Authorization: bearer cc768d10ac1c55f1215c8d9e0558f83dd94fa17b3914ea9783c86a2d73a42e13"})
    Call<UserResponse> createUser(@Body User prams);
}
