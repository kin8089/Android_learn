package com.cookandroid.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.login2.Register.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
   private final String BASEURL = "http://192.168.0.247:8000/";

    private EditText et_rid, et_rpass1, et_rpass2, et_remail;
    private Button btn_register;
    private RegisterService registerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Retrofit Register");

        et_rid = findViewById(R.id.et_rid);
        et_remail = findViewById(R.id.et_remail);
        et_rpass1 = findViewById(R.id.et_rpass1);
        et_rpass2 = findViewById(R.id.et_rpass2);
        btn_register = findViewById(R.id.btn_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerService = retrofit.create(RegisterService.class);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
            }
        });
    }

    private void createPost() {
        String Username = et_rid.getText().toString();
        String Email = et_remail.getText().toString();
        String Password1 = et_rpass1.getText().toString();
        String Password2 = et_rpass2.getText().toString();


        Post post = new Post(Username,Email,Password1,Password2);

        Call<Post> call = registerService.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}