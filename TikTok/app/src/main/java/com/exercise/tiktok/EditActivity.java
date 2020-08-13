package com.exercise.tiktok;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exercise.tiktok.data.MyValues;
import com.exercise.tiktok.data.UserInfo;
import com.exercise.tiktok.views.SettingItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private SettingItem name, code, sign, xingbie, birth;
    private Button back, img;
    private Context context;
    private GetInfoTask getInfoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bindView();
        initData();
    }

    private void initData(){
        getInfoTask.execute();

    }


    protected void bindView() {
        context = EditActivity.this;
        getInfoTask = new GetInfoTask();
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
                                UpdateSexTask updateSexTask = new UpdateSexTask();
                                //Toast.makeText(context, "你是男的", Toast.LENGTH_SHORT).show();
                                updateSexTask.execute(UserInfo.phone,"男");
                                xingbie.setDescribeName("男");
                            }
                        })
                        .setPositiveButton("女", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UpdateSexTask updateSexTask = new UpdateSexTask();
                                //Toast.makeText(context, "你是女的", Toast.LENGTH_SHORT).show();
                                updateSexTask.execute(UserInfo.phone,"女");
                                xingbie.setDescribeName("女");
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
                        UpdateBirthDayTask updateBirthDayTask = new UpdateBirthDayTask();
                        String date =  year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
                        //String result = "你选择的是" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
                        //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        updateBirthDayTask.execute(UserInfo.phone,date);
                        birth.setDescribeName(date);
                    }
                }
                        , cale1.get(Calendar.YEAR)
                        , cale1.get(Calendar.MONTH)
                        , cale1.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    private class UpdateSexTask extends AsyncTask<String, Void, Integer> {
        private String response_message;
        private String resopnse_data;
        private int ret_code;

        @Override
        protected Integer doInBackground(String... strings) {
            String phone = strings[0];
            String sex = strings [1];
            String url = MyValues.SERVER_URL + "update_sex";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", phone)
                    .add("sex", sex)
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
                    Toast.makeText(EditActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(EditActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(EditActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }



    private class UpdateBirthDayTask extends AsyncTask<String, Void, Integer> {
        private String response_message;
        private String resopnse_data;
        private int ret_code;

        @Override
        protected Integer doInBackground(String... strings) {
            String phone = strings[0];
            String birthday = strings [1];
            String url = MyValues.SERVER_URL + "update_birthday";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", phone)
                    .add("birthday", birthday)
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
                    Toast.makeText(EditActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(EditActivity.this, resopnse_data, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(EditActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    private class GetInfoTask extends AsyncTask<String,Void,Integer>{
        private String response_message;
        private String[] resopnse_data;
        @Override
        protected Integer doInBackground(String... strings) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(MyValues.SERVER_URL+"get_info/"+"1").build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                response_message = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
                return 3;
            }
            try {
                JSONObject jsonObject = new JSONObject(response_message);
                JSONObject jsonObject_user = new JSONObject(jsonObject.get("data").toString());
                UserInfo.nickname = jsonObject_user.get("nickname").toString();
                UserInfo.phone = jsonObject_user.get("phone").toString();
                UserInfo.account = jsonObject_user.get("account").toString();
                UserInfo.introduction = jsonObject_user.get("introduction").toString();
                UserInfo.sex = jsonObject_user.get("sex").toString();
                UserInfo.birthday = jsonObject_user.get("birthday").toString();
                UserInfo.region = jsonObject_user.get("region").toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return 2;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case 1:
                    name.setDescribeName(UserInfo.nickname);
                    code.setDescribeName(UserInfo.account);
                    sign.setDescribeName(UserInfo.introduction);
                    xingbie.setDescribeName(UserInfo.sex);
                    birth.setDescribeName(UserInfo.birthday);
                    break;
                case 2:
                    name.setDescribeName("获取数据失败");
                    Toast.makeText(EditActivity.this,"数据获取失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    name.setDescribeName("请求服务器失败");
                    Toast.makeText(EditActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}