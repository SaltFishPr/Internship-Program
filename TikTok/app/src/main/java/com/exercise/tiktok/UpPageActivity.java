package com.exercise.tiktok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.exercise.tiktok.adapter.FragmentAdapter;
import com.exercise.tiktok.fragment.VideoCardFragment;

import java.util.ArrayList;

public class UpPageActivity extends AppCompatActivity {

    private Button back;
    private XTabLayout tabTitle;
    ViewPager viewPager;
    FragmentAdapter videoCardAdapter;
    ArrayList<Fragment> fragments;
    Context context;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_page);
        bindView();
    }

    protected void bindView() {
        context = UpPageActivity.this;
        Intent intent = getIntent();
        name = findViewById(R.id.user_name);
        name.setText(intent.getStringExtra("name"));
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tabTitle = findViewById(R.id.tab_title);
        tabTitle.addTab(tabTitle.newTab().setText("作品"));
        tabTitle.addTab(tabTitle.newTab().setText("转发"));
        tabTitle.addTab(tabTitle.newTab().setText("喜欢"));
        fragments = new ArrayList<>();
        fragments.add(new VideoCardFragment());
        fragments.add(new VideoCardFragment());
        fragments.add(new VideoCardFragment());
        FragmentAdapter videoCardAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, new String[]{"作品", "转发", "喜欢"});
        //必须要设置title，并且title顺序与fragment顺序相对应，数量也是
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(videoCardAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(0).select();

    }
}