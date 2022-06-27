package com.cookandroid.conn4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherActivity extends AppCompatActivity
{
    String name, hobby;
    TextView get_name, get_hobby;
    Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        get_name = (TextView) findViewById(R.id.get_name);
        get_hobby = (TextView) findViewById(R.id.get_hobby);
        back_btn = (Button) findViewById(R.id.back_activity);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        hobby = intent.getStringExtra("hobby");
        Log.e("OtherActivity", "받아온 이름 : " + name + ", 취미 : " + hobby);

        // 레트로핏으로 서버에서 값을 받아온다
        getNameHobby(name, hobby);

        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void getNameHobby(String name, String hobby)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Person> call = apiInterface.getNameHobby(name, hobby);
        call.enqueue(new Callback<Person>()
        {
            @Override
            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    String getted_name = response.body().getName();
                    String getted_hobby = response.body().getHobby();
                    Log.e("getNameHobby()", "서버에서 이름 : " + getted_name + ", 서버에서 받아온 취미 : " + getted_hobby);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t)
            {
                Log.e("getNameHobby()", "에러 : " + t.getMessage());
            }
        });
    }
}