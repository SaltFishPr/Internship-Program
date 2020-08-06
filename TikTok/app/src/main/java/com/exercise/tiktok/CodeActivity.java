package com.exercise.tiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CodeActivity extends AppCompatActivity {

    private Button back;
    private TextView baocun;
    private EditText code;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        bindView();
    }

    protected void bindView(){
        context = CodeActivity.this;
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
                Toast.makeText(context,"新抖音号是"+code.getText().toString().toString(),Toast.LENGTH_LONG).show();
            }
        });
        code = findViewById(R.id.code_edit);
    }
}