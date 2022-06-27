package com.cookandroid.login2.login;

import com.cookandroid.login2.MemberVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {

    @POST("accounts/login/")
    Call<MemberVO> Login(@Body MemberVO memberVO);
}
