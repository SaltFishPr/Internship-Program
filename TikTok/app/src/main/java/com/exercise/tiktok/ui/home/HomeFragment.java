package com.exercise.tiktok.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.exercise.tiktok.EditActivity;
import com.exercise.tiktok.R;
import com.exercise.tiktok.SettingActivity;
import com.exercise.tiktok.data.MyValues;
import com.exercise.tiktok.data.UserInfo;
import com.exercise.tiktok.fragment.VideoCardFragment;
import com.exercise.tiktok.adapter.FragmentAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private TextView userTv,accountTv,signTv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = root.findViewById(R.id.btn_setting);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"进入设置界面",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        userTv = (TextView)root.findViewById(R.id.user_name);
        accountTv = (TextView)root.findViewById(R.id.user_account);
        signTv = (TextView)root.findViewById(R.id.user_sign);
        GetInfoTask getInfoTask = new GetInfoTask();
        getInfoTask.execute();
        Button edit = root.findViewById(R.id.user_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });


        XTabLayout tabTitle = root.findViewById(R.id.tab_title);
        tabTitle.addTab(tabTitle.newTab().setText("作品"));
        tabTitle.addTab(tabTitle.newTab().setText("转发"));
        tabTitle.addTab(tabTitle.newTab().setText("喜欢"));
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new VideoCardFragment());
        fragments.add(new VideoCardFragment());
        fragments.add(new VideoCardFragment());
        FragmentAdapter videoCardAdapter = new FragmentAdapter(getChildFragmentManager(),fragments,new String[] {"作品", "转发","喜欢"});
        //必须要设置title，并且title顺序与fragment顺序相对应，数量也是
        ViewPager viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(videoCardAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(0).select();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetInfoTask getInfoTask = new GetInfoTask();
        getInfoTask.execute();
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
                UserInfo.account = jsonObject_user.get("account").toString();
                UserInfo.introduction = jsonObject_user.get("introduction").toString();
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
                    userTv.setText("姓名:"+UserInfo.nickname);
                    accountTv.setText("抖音号"+UserInfo.account);
                    signTv.setText("个人简介:"+UserInfo.introduction);
                    break;
                case 2:
                    userTv.setText("姓名:"+"请检查网络连接");
                    accountTv.setText("抖音号"+"请检查网络连接");
                    signTv.setText("个人简介:"+"请检查网络连接");
                    break;
                default:
                    userTv.setText("姓名:"+"服务器请求失败");
                    accountTv.setText("抖音号"+"服务器请求失败");
                    signTv.setText("个人简介:"+"服务器请求失败");
                    break;
            }
        }
    }


}