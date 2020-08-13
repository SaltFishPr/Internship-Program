package com.exercise.tiktok.ui.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.exercise.tiktok.R;
import com.exercise.tiktok.adapter.FragmentAdapter;
import com.exercise.tiktok.fragment.ShouyeFragment;
import com.exercise.tiktok.fragment.VideoFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        XTabLayout tabTitle = root.findViewById(R.id.tab_title);
        tabTitle.addTab(tabTitle.newTab().setText("关注"));
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouyeFragment());
        fragments.add(new ShouyeFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),fragments,new String[] {"关注", "推荐"});
        //必须要设置title，并且title顺序与fragment顺序相对应，数量也是
        ViewPager viewPager = root.findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentAdapter);
        tabTitle.setupWithViewPager(viewPager);
        tabTitle.getTabAt(1).select();
        return root;
    }
    
}