package com.cookandroid.conn;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPI {
    //GET버튼 누르면 여기로 와서 동작한다.
    @GET("versions/")
    Call<List<PostItem>> get_versions();

    //POST버튼 누르면 여기로 와서 동작한다.
    @POST("user/")
    Call<PostItem> post_versions( @Body PostItem diary);

    @GET("/versions/{pk}/")
    Call<PostItem> get_versions_pk(@Path("pk") int pk);
}