package com.cookandroid.conn2;

import okhttp3.RequestBody;
import okhttp3.internal.Version;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public class NetworkService {

    @POST("/api/versions/")
    Call<Versions>post_version(@Body Version version);

    @PATCH("/api/versions/{pk}/")
    Call<Versions>pathch_version(@Path("pk") int pk,@Body Version version);

    @DELETE("/api/versions/{pk}/")
    Call<Versions>delete_version(@Path("pk") int pk);

    @GET("/api/versions/")
    Call<Versions>get_version();

    @POST("/api/versions/{pk}/")
    Call<Versions>get_pk_version(@Path("pk") int pk);

    @POST("/api/restaurants/")
    Call<restaurants>post_restaurants(@Body Restaurants restaurants);

    @PATCH("/api/restaurants/{pk}/")
    Call<restaurants>pathch_restaurants(@Path("pk") int pk,@Body Restaurants restaurants);

    @DELETE("/api/restaurants/")
    Call<restaurants>delete_restaurants();

    @GET("/api/restaurants/{pk}/")
    Call<restaurants>get_pk_restaurants(@Path("pk") int pk);

    @POST("/api/weathers/{pk}/restaurants_list/")
    Call<restaurants>get_weather_pk_restaurants(@Path("pk") int pk);
}
