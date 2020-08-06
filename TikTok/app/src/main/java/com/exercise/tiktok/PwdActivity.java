package com.exercise.tiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PwdActivity extends AppCompatActivity {

    private EditText pwd_raw,pwd_new_1,pwd_new_2;
    private Button back;
    private TextView baocun;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        bindView();
    }

    protected void bindView(){
        context = PwdActivity.this;
        pwd_raw = findViewById(R.id.raw_pwd);
        pwd_new_1 = findViewById(R.id.new_pwd_1);
        pwd_new_2 = findViewById(R.id.new_pwd_2);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        baocun = findViewById(R.id.baocun);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baocun();
            }
        });
    }

    public void baocun(){
        String pw,pw1,pw2;
        pw1 = pwd_new_1.getText().toString().trim();
        pw2 = pwd_new_2.getText().toString().trim();
        if(!pw1.equals(pw2)){
            Toast.makeText(context,"请确认两次新密码相等",Toast.LENGTH_LONG).show();
            return;
        }

    }
}