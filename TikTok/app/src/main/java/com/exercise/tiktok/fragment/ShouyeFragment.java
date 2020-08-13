package com.exercise.tiktok.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.exercise.tiktok.R;

import com.exercise.tiktok.adapter.VideoAdapter;
import com.exercise.tiktok.bean.VideoBean;
import com.exercise.tiktok.utils.OnViewPagerListener;
import com.exercise.tiktok.utils.ViewPagerLayoutManager;


import java.util.ArrayList;
import java.util.List;

public class ShouyeFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private VideoAdapter myAdapter;
    private int curPlayPos = -1;
    private VideoView videoView;
    private List<VideoBean> datas;
    private ViewPagerLayoutManager viewPagerLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shouye, container, false);
        refreshLayout = root.findViewById(R.id.refreshlayout);
        setRefreshEvent();
        recyclerView = root.findViewById(R.id.recyclerview);
        datasInit();
        myAdapter = new VideoAdapter(getActivity(), datas);

        recyclerView.setAdapter(myAdapter);
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                recyclerView.releasePointerCapture();
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                videoView = recyclerView.findViewById(R.id.vv);
                videoView.start();
                recyclerView.scrollToPosition(position);
            }
        });
        return root;
    }

    //上拉刷新
    private void setRefreshEvent() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        refreshLayout.setRefreshing(false);
                    }
                }.start();
            }
        });
    }


    //模拟数据
    protected void datasInit() {
        VideoBean videoBean1 = new VideoBean(R.drawable.user_template, R.raw.video1,
                "姓名", "这是一段描述", "200", "300", "400");
        VideoBean videoBean2 = new VideoBean(R.drawable.user_template, R.raw.video2,
                "jl", "jl发的视频", "9999w", "9999w", "9999w");
        VideoBean videoBean3 = new VideoBean(R.drawable.user_template, R.raw.video3,
                "wws", "wws发的视频", "100", "200", "300");
        datas = new ArrayList<>();
        datas.add(videoBean1);
        datas.add(videoBean2);
        datas.add(videoBean3);
    }

}
