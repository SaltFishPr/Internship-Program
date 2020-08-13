package com.exercise.tiktok;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exercise.tiktok.views.SettingItem;

public class SettingActivity extends AppCompatActivity {

    private SettingItem user_code, pwd_edit, logout;
    private Button back;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initData();
        bindView();
    }


    private void initData(){

    }


    protected void bindView() {
        context = SettingActivity.this;
        back = findViewById(R.id.btn_back);
        user_code = findViewById(R.id.text_code);
        pwd_edit = findViewById(R.id.pwd_edit);
        logout = findViewById(R.id.logout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pwd_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, PwdActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert = null;
                builder = new AlertDialog.Builder(context);
                alert = builder.setMessage("退出？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).create();             //创建AlertDialog对象
                alert.show();
            }
        });
    }
}