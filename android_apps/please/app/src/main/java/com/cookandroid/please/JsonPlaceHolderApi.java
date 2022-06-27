package com.cookandroid.please;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("accounts/create")
    Call<List<Post>> getPosts();

}
