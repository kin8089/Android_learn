package com.cookandroid.login2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.login2.login.ILoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnGoSignup, btnLogin;
    EditText etLoginId, etLoginPw, etLoginName;
    String username, password, email;
    private RetrofitClient retrofitClient;
    private InitMyapi initMyapi;
    private Context mContext;
    InputMethodManager imm;
    private LinearLayout loginLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        etLoginName = findViewById(R.id.et_login_name);
        //etLoginId = findViewById(R.id.et_login_id);
        etLoginPw = findViewById(R.id.et_login_pw);
        btnGoSignup = findViewById(R.id.btn_go_signup);
        btnLogin = findViewById(R.id.btn_login);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        loginLayout = findViewById(R.id.login_parent);


        loginLayout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                hideKeyboard();
                return false;
            }
        });

        btnGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog();
                Login();
            }
        });

    }

    public void Login() {
        username = etLoginName.getText().toString();
        //email = etLoginId.getText().toString();
        password = etLoginPw.getText().toString();

        CustomSharedPreferences.setString(mContext, "username", username);
        CustomSharedPreferences.setString(mContext, "email", email);


        //LoginRequest loginRequest = new LoginRequest(username, password);

        retrofitClient = RetrofitClient.getInstance();
        initMyapi = RetrofitClient.getRetrofitInterface();

        initMyapi.getLoginResponse(username, password).enqueue(new Callback<LoginResponse>() {
            //initMyapi.getLoginResponse(username, email, password).enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("Retrofit", "Data fetch SUCCESS");
                System.out.println(response);

                if (response.isSuccessful() && response.code() == 200) {
                    LoginResponse result = response.body();
                    //   String username = result.getUser().getUsername();
                    //   String email = result.getUser().getEmail();
                    //토큰 추가
                    String authToken = result.getAccess();
                    //CustomSharedPreferences.clear(mContext);
                    CustomSharedPreferences.setString(mContext, "access", authToken);
                    retrofitClient.RetrofitConnection(authToken);

                    //   MyApplication.prefs.setToken(token);
                    System.out.println("Token 저장 확인용: " + authToken);
                    loginshowDialog();


                }
                else {
                    Log.e("로그인 실패", "FAIL");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                Log.e("연결실패", "오류");

            }
        });
    }

    void loginshowDialog() {
        username = etLoginName.getText().toString();

        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(username + "님!")
                .setMessage("로그인하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(LoginActivity.this, "로그인되었습니다", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                        //progressDialog();
                    }
                });
        msgBuilder.show();
    }
}