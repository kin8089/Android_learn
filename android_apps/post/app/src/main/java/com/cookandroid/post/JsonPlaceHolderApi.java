package com.cookandroid.post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("user")
    Call<Post> createPost(@Body Post post);
}

