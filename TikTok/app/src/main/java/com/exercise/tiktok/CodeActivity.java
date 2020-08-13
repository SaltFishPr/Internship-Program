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
                //Toast.makeText(context,"新抖音号是"+code.getText().toString().toString(),Toast.LENGTH_LONG).show();
                UpdateAccountTask updateAccountTask = new UpdateAccountTask();
                updateAccountTask.execute(UserInfo.phone,code.getText().toString().toString());

            }
        });
        code = findViewById(R.id.code_edit);
    }

    private class UpdateAccountTask extends AsyncTask<String,Void,Integer>{
        private String response_message;
        private String resopnse_data;
        private int ret_code;
        @Override
        protected Integer doInBackground(String... strings) {
            String phone = strings[0];
            String account = strings [1];
            String url = MyValues.SERVER_URL + "update_account";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", phone)
                    .add("account", account)
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
                    Toast.makeText(CodeActivity.this,resopnse_data,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CodeActivity.this,EditActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(CodeActivity.this,resopnse_data,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(CodeActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}