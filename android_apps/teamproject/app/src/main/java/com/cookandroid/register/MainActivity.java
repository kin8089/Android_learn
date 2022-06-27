package com.cookandroid.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.GnssMeasurementsEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void checkAccount(String userId, String userPass) {
            private final  String TAG = getClass().getSimpleName();
            //
            RequestBody checkID = RequestBody.create(MediaType.parse("text/plain"), userId);
            RequestBody checkPW = RequestBody.create(MediaType.parse("text/plain"), userPass);

            // Retrofit 객체 생성
            Retrofit.Builder builder3 = new Retrofit.Builder()
                    .baseUrl("https://50d050ed24bc.ngrok.io")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit3 = builder3.build();

            MyAPI myAPI3 = retrofit3.create(MyAPI.class);

            // post 한다는 request를 보내는 부분.
            Call<ResponseBody> call = myAPI3.post_check(checkID, checkPW);
            // 만약 서버로 부터 response를 받는다면.
            call.enqueue(new GnssMeasurementsEvent.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){

                        Log.d(TAG,"계정 확인 완료용!!"+response.toString());
                        Toast.makeText(getApplicationContext(),"계정 확인이용!!!!!",Toast.LENGTH_SHORT).show();

//                     로그인. Main Activity2 를 호출한다. (갤러리와 이미지 처리 버튼이 나오는 부분이다)
//                     text view 내의 값들이 db에 있는 경우
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        intent.putExtra("firstKeyName",userId); // Verify된 경우 userId 다음 액티비티로 전달하기
                        startActivity(intent);
                    }else {
                        Log.d(TAG,"Post Status Code ㅠㅠ : " + response.code());
                        Toast.makeText(getApplicationContext(),"계정 없어용!!!!!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,response.errorBody().toString());
                        Log.d(TAG,call.request().body().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());

                }
            });
        }
    }
}