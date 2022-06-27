package com.cookandroid.login2.login;

import com.cookandroid.login2.vo.MemberVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginService {

    @POST("accounts/login")
    Call<MemberVO> getMember(@Query("Username")String Username, @Query("Password") String Password);
}
