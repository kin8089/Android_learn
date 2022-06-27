package com.cookandroid.get;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("user")
    Call<List<Post>> getPost();
}
