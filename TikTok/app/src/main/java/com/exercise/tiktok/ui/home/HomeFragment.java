package com.exercise.tiktok.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.exercise.tiktok.EditActivity;
import com.exercise.tiktok.MainActivity;
import com.exercise.tiktok.R;
import com.exercise.tiktok.SettingActivity;
import com.exercise.tiktok.fragment.VideoCardFragment;
import com.exercise.tiktok.fragment.VideoFragment;
import com.exercise.tiktok.ui.camera.CameraFragment;
import com.exercise.tiktok.ui.main.MainFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),fragments,new String[] {"作品", "转发","喜欢"});
        //必须要设置title，并且title顺序与fragment顺序相对应，数量也是
        ViewPager viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(myAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(0).select();
        return root;
    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        private ArrayList<? extends Fragment> items;
        private String[] mTitles;

        public MyAdapter(FragmentManager fm, ArrayList< ? extends Fragment> items, String[] mTitles) {
            super(fm);
            this.items = items;
            this.mTitles = mTitles;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size()==0 ? 0 :items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;

        }
        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}