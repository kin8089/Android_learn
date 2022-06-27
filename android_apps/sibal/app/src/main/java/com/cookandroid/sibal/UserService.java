package com.cookandroid.sibal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("accounts/login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


}
