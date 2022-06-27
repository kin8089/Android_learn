package com.cookandroid.login2.Register;

import com.cookandroid.login2.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("accounts/create/")
    Call<Post> createPost(@Body Post post);
}

