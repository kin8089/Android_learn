package com.cookandroid.login2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cookandroid.login2.Register.RegisterService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class SignupActivity extends AppCompatActivity {

        Button btnSignup, btnPwCheck;
        EditText etSignupId, etSignupPw1, etSignupPw2, etSignupName;
        String username, email, password1, password2;
        registDB rdb;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            //가입항목
            etSignupId = findViewById(R.id.et_signup_id);
            etSignupPw1 = findViewById(R.id.et_signup_pw1);
            etSignupPw2 = findViewById(R.id.et_signup_pw2);
            etSignupName = findViewById(R.id.et_signup_name);


            //회원가입 버튼 누르면 실행
            btnSignup = findViewById(R.id.btn_signup);
            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username = etSignupName.getText().toString();
                    email = etSignupId.getText().toString();
                    password1 = etSignupPw1.getText().toString();
                    password2 = etSignupPw2.getText().toString();

                    rdb = new registDB();
                    rdb.execute();

                }
            });
        }

        //AsyncTask 사용
        class registDB extends AsyncTask<Void, Integer, String> {
            @Override
            protected String doInBackground(Void... unused) {

                //파라미터 만들기
                String param = "email=" + email + "&password1=" + password1 + "&password2=" + password2 + "&username=" + username + "";
                String response = null;
                String success = null;
                try {

                    //서버연결
                    URL url = new URL(
                            "http://3.36.39.226:8700/accounts/register/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.connect();

                    //to서버
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    Log.e("param", "전송: " + param);
                    outs.flush();
                    outs.close();

                    //from서버
                    String res;
                    InputStream is = null;
                    ByteArrayOutputStream baos = null;

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        is = conn.getInputStream();
                        baos = new ByteArrayOutputStream();
                        byte[] byteBuffer = new byte[1024];
                        byte[] byteData = null;
                        int nLength = 0;
                        while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                            baos.write(byteBuffer, 0, nLength);
                        }

                        byteData = baos.toByteArray();
                        res = new String(byteData);
                        JSONObject responseJSON = new JSONObject(res);

                    }


                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return success;
            }
        }

    }