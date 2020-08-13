package com.exercise.tiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.tiktok.data.MyValues;
import com.exercise.tiktok.data.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        pw = pwd_raw.getText().toString().trim();
        pw1 = pwd_new_1.getText().toString().trim();
        pw2 = pwd_new_2.getText().toString().trim();
        if(!pw1.equals(pw2)){
            Toast.makeText(context,"请确认两次新密码相等",Toast.LENGTH_LONG).show();
            return;
        }else{
            UpdatePwdTask updatePwdTask = new UpdatePwdTask();
            //第一个参数应该为当前用户的 手机号
            updatePwdTask.execute(UserInfo.phone,pw,pw1);
            return;
        }

    }

    private class UpdatePwdTask extends AsyncTask<String,Void,Integer>{
        private String response_message;
        private String resopnse_data;
        private int ret_code;

        @Override
        protected Integer doInBackground(String... strings) {
            String phone = strings[0];
            String pwd = strings[1];
            String new_pwd = strings[2];
            // Url后续的ip地址和端口应该进行更改
            String url = MyValues.SERVER_URL + "update_password";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", phone)
                    .add("old_password", pwd)
                    .add("new_password",new_pwd)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                response_message = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return 3;
            }
            try {
                JSONObject jsonObject = new JSONObject(response_message);
                resopnse_data = jsonObject.get("data").toString();
                ret_code = (int) jsonObject.get("ret_code");
            } catch (JSONException e) {
                e.printStackTrace();
                return 2;
            }
            return ret_code;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case 1:
                    Toast.makeText(PwdActivity.this,resopnse_data,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PwdActivity.this,SettingActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(PwdActivity.this,resopnse_data,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(PwdActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

}