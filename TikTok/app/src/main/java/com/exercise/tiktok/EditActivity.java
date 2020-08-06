package com.exercise.tiktok;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exercise.tiktok.views.SettingItem;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private SettingItem name, code, sign, xingbie, birth;
    private Button back, img;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bindView();
    }

    protected void bindView() {
        context = EditActivity.this;
        img = findViewById(R.id.user_img_edit);
        img.setOnClickListener(this);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(this);

        name = findViewById(R.id.user_name_edit);
        name.setOnClickListener(this);
        code = findViewById(R.id.user_code_edit);
        code.setOnClickListener(this);
        sign = findViewById(R.id.user_sign_edit);
        sign.setOnClickListener(this);
        xingbie = findViewById(R.id.user_xingbie_edit);
        xingbie.setOnClickListener(this);
        birth = findViewById(R.id.user_birth_edit);
        birth.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.user_img_edit:

                Toast.makeText(context, "编辑头像", Toast.LENGTH_LONG).show();
                break;
            case R.id.user_name_edit:
                startActivity(new Intent(context, NameActivity.class));
                Toast.makeText(context, "编辑姓名", Toast.LENGTH_LONG).show();
                break;
            case R.id.user_code_edit:
                startActivity(new Intent(context, CodeActivity.class));
                Toast.makeText(context, "编辑抖音号", Toast.LENGTH_LONG).show();
                break;
            case R.id.user_sign_edit:
                startActivity(new Intent(context, SignActivity.class));
                Toast.makeText(context, "编辑简介", Toast.LENGTH_LONG).show();
                break;
            case R.id.user_xingbie_edit:
                Toast.makeText(context, "编辑性别", Toast.LENGTH_LONG).show();
                AlertDialog alert = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                alert = builder.setMessage("你的性别？")
                        .setNegativeButton("男", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "你是男的", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("女", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "你是女的", Toast.LENGTH_SHORT).show();
                            }
                        }).create();             //创建AlertDialog对象
                alert.show();
                break;
            case R.id.user_birth_edit:
                Toast.makeText(context, "编辑生日", Toast.LENGTH_LONG).show();
                Calendar cale1 = Calendar.getInstance();
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        //这里获取到的月份需要加上1哦~
                        String result = "你选择的是" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    }
                }
                        , cale1.get(Calendar.YEAR)
                        , cale1.get(Calendar.MONTH)
                        , cale1.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}