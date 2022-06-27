package com.cookandroid.conn4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("example_select.php")
    Call<Person> getNameHobby(
            @Query("name") String name,
            @Query("hobby") String hobby
    );
}