package com.cookandroid.conn2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolerApi {

    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
