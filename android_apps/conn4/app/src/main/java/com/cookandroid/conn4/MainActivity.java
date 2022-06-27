package com.cookandroid.conn4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    EditText edit_name, edit_hobby;
    Button search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_hobby = (EditText) findViewById(R.id.edit_hobby);
        search_btn = (Button) findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = edit_name.getText().toString();
                String hobby = edit_hobby.getText().toString();
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("hobby", hobby);
                startActivity(intent);
            }
        });
    }
}