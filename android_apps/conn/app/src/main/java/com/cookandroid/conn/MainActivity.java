package com.cookandroid.conn;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String BASE_URL = "http://192.168.0.247:8000/";
    private MyAPI mMyAPI;
    private TextView mListTv;
    private EditText testContent1;
    private EditText testContent2;
    private Button mGetButton;
    private Button mPostButton;
    private Button mPatchButton;
    private Button mDeleteButton;

    String versions_weather = "2";
    String versions_img;	//이미지 필드에는 NULL만 차게 할것!
    String versions_date = "";
    String versions_todayme = "";
    String versions_tomorrowme = "";
    String versions_content ="default content";
    String versions_title="default title";
    int versions_id=13;

    //레트로핏 생성하는 부분이다. create 된 레트로핏 이름은 mMyAPI
    private void initMyAPI(String baseUrl){
        Log.d(TAG,"initMyAPI : " + baseUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }

    private final  String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testContent1 = findViewById(R.id.testContent1);
        testContent2 = findViewById(R.id.testContent2);

        mListTv = findViewById(R.id.result1);

        mGetButton = findViewById(R.id.button1);
        mGetButton.setOnClickListener(this);
        mPostButton = findViewById(R.id.button2);
        mPostButton.setOnClickListener(this);
        mPatchButton = findViewById(R.id.button3);
        mPatchButton.setOnClickListener(this);
        mDeleteButton = findViewById(R.id.button4);
        mDeleteButton.setOnClickListener(this);

        //BASE_URL기준으로 레트로핏 Call 객체 획득
        initMyAPI(BASE_URL);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        //GET버튼 클릭시
        if( v == mGetButton){
            Log.d(TAG,"GET");
            //Service이용해서 CALL 보낸다
            Call<List<PostItem>> getCall = mMyAPI.get_versions();
            //Call 객체 네트워킹 시킴
            getCall.enqueue(new Callback<List<PostItem>>() {
                @Override
                //반응 오면 onResponse
                public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                    //반응이 성공이면
                    if( response.isSuccessful()){
                        List<PostItem> mList = response.body();
                        String result ="";
                        for( PostItem item : mList){
                            result += "title : " + item.getTitle() + " text: " + item.getContent() + "\n";
                        }

                        mListTv.setText(result);
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<PostItem>> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }

        //EditText의 입력 내용을 받아서 데이터 필드에 저장
        versions_title = testContent1.getText().toString();
        versions_content = testContent2.getText().toString();
        //POST버튼 클릭시
        if(v == mPostButton){
            Log.d(TAG,"POST");
            //모델 객체 item생성
            PostItem item = new PostItem(versions_title, versions_content);

            //Service이용해서 CALL 보낸다
            Call<PostItem> postCall = mMyAPI.post_versions(item);
            //Call 객체 네트워킹 시킴
            postCall.enqueue(new Callback<PostItem>() {
                @Override
                //반응 오면 onResponse
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    //반응이 성공이면
                    if(response.isSuccessful()){
                        Log.d(TAG,"등록 완료");
                    }else {
                        Log.d(TAG, item.getversions_id()+item.getTitle());
                        //안될 경우 item에 저장값 보기위한 로그출력
                        Log.d(TAG,new Gson().toJson(item));
                        //상태코드 로그 출력
                        Log.d(TAG,"Status Code : " + response.code());

                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });

        }
    }
}