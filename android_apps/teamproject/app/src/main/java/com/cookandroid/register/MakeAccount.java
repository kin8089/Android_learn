package com.cookandroid.register;

import android.util.Log;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeAccount {
    private final  String TAG = getClass().getSimpleName();

    private void registerAccount(String userId, String userPass) {
        RequestBody identify = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), userPass);

        // Retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.247:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPI myApi = retrofit.create(MyAPI.class);

        // POST request 보내는 파트안드로이드 스튜디오 로그 출력
        Call<ResponseBody> call = myApi.post_accounts(identify, password);

        // 서버에서 response 받을 시
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "계정 등록");
                }else {
                    Log.d(TAG, "Post Status Code : " + response.code());
                    Log.d(TAG, response.errorBody().toString());
                    Log.d(TAG, call.request().body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Fail msg : " + t.getMessage());

            }
        });
    }
}