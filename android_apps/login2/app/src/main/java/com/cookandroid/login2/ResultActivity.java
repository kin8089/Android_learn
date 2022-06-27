package com.cookandroid.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cookandroid.login2.vo.MemberVO;

public class ResultActivity extends AppCompatActivity {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result");

        tv_result = findViewById(R.id.tv_result);

        Intent intent = getIntent();

        MemberVO memberVO = intent.getParcelableExtra("memberVO");

        tv_result.setText(memberVO.getUsername() + "님 반갑습니다");
    }
}