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

public class SignActivity extends AppCompatActivity {

    private Button back;
    private TextView baocun;
    private EditText sign;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        bindView();
    }

    protected void bindView(){
        context = SignActivity.this;
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
                //Toast.makeText(context,"新简介是"+sign.getText().toString().toString(),Toast.LENGTH_LONG).show();
                UpdateSignTask updateSignTask = new UpdateSignTask();
                updateSignTask.execute(UserInfo.phone,sign.getText().toString().toString());
            }
        });
        sign = findViewById(R.id.sign_edit);
    }


    private class UpdateSignTask extends AsyncTask<String,Void,Integer> {
        private String response_message;
        private String resopnse_data;
        private int ret_code;

        @Override
        protected Integer doInBackground(String... strings) {
            String phone = strings[0];
            String introduction = strings[1];
            String url = MyValues.SERVER_URL + "update_introduction";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", phone)
                    .add("introduction", introduction)
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
                    Toast.makeText(SignActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignActivity.this, EditActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(SignActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(SignActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}