package com.cookandroid.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationExitInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.login2.login.ILoginService;
import com.cookandroid.login2.vo.MemberVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register2;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Retrofit Login");

        setRetrofitInIt();  // 초기화

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register2 = findViewById(R.id.btn_register2);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btn_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void setRetrofitInIt(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.247:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void login() {
        String Username = et_id.getText().toString();
        String Password = et_pass.getText().toString();

        ILoginService service = retrofit.create(ILoginService.class);
        Call<MemberVO> call = service.getMember(Username, Password);

        call.enqueue(new Callback<MemberVO>() {
            @Override
            public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                MemberVO memberVO = response.body();

                if(memberVO != null){
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("memberVO", memberVO);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "회원이 아닙니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberVO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }

}