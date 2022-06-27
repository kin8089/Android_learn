package com.cookandroid.conn2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

import org.w3c.dom.Text;

import okhttp3.internal.Version;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private NetworkService networkService;
    @BindView(R.id.tv1) TextView tv1;
    @BindView(R.id.tv2) TextView tv2;
    @BindView(R.id.tv3) TextView tv3;
    @BindView(R.id.tv4) TextView tv4;
    @BindView(R.id.tv5) TextView tv5;
    @BindView(R.id.tv6) TextView tv6;
    @BindView(R.id.tv7) TextView tv7;
    @BindView(R.id.tv8) TextView tv8;
    @BindView(R.id.tv9) TextView tv9;

    @OnClick(R.id.bt1)
    public void bt1_Click(){
        //GET
        Call<List<Version>> versionCall = networkService.get_version();
        versionCall.enqueue(new Callback<List<Version>>() {
            @Override
            public void onResponse(Call<List<Version>> call, Response<List<Version>> response) {
                if(response.isSuccessful()){
                    List<Version> versionList = response.body();

                    String version_txt = "";
                    for(Version version : versionList){
                        version_txt += version.getVersion() + "\n";
                    }

                    tv1.setText(version_txt);
                }else {
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code: " + StatusCode);
                }
            }
            @Override
            public void onFailure(Call<List<Version>> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message: " + t.getMessage());

            }
        });
    }

    @OnClick(R.id.bt2)
    public void bt2_Click(){
        //GET
        Version version = new Version();
        Version.setVersion("1.0.0.1");
        Call<Version> postCall = networkService.post_version(version);
        postCall.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                if(response.isSuccessful()){
                    tv2.setText("등록");
                }else{
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code:" + StatusCode);
                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message: " + t.getMessage());

            }
        });

    }

    @OnClick(R.id.bt3)
    public void bt3_click(){
        Version version = new Version();
        version.setVersion("1.0.0.0.1");
        Call<Version> patchCall = networkService.pathch_version(1,version);
        patchCall.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                if(response.isSuccessful()){
                    tv3.setText("업데이트");
                }else{
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code:" + StatusCode);
                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message: " + t.getMessage());
            }
        });
    }

    @OnClick(R.id.bt4)
    public void bt4_click(){
        Call<Version> deleteCall = networkService.delete_version(1);
        deleteCall.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                if(response.isSuccessful()){
                    tv3.setText("삭제");
                }else{
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code:" + StatusCode);
                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message: " + t.getMessage());
            }
        });
    }

    @OnClick(R.id.bt5)
    public void bt5_click(){
        Call<List<Restaurant>> getCall = networkService.get_restaurants();
        getCall.enqueue(new Callback<List<Version>>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                if(response.isSuccessful()){
                    tv3.setText("삭제");
                }else{
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code:" + StatusCode);
                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message: " + t.getMessage());
            }
        });
    }
    private final String BASEURL = "https://jsonplaceholder.typicode.com/";
    private TextView textView;

    private JsonPlaceHolerApi jsonPlaceHolerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolerApi = retrofit.create(JsonPlaceHolerApi.class);

        createPost();
    }

    private void createPost() {
        Post post = new Post(23, "new title", "new text");

        Call<Post> call = jsonPlaceHolerApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "Id: " + postResponse.getId() + "\n";
                content += "User Id: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


}