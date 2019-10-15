package com.example.rubikscube_test.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.example.rubikscube_test.Data.TeachViewModel;
import com.example.rubikscube_test.HomeActivity;
import com.example.rubikscube_test.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class TeachFragment extends Fragment {

    List<TeachViewModel> data;
    Context context;
    List<Fragment> fragmentList;
    List<String> titleList;
    public RequestQueue requestQueue;

    ViewPager viewPager;
    TabLayout tabLayout;

    public TeachFragment(Context context) {
        data = new ArrayList<>();
        this.context = context;
        this.requestQueue = ( (HomeActivity) context).requestQueue;
        this.fragmentList = new ArrayList<>();
        this.titleList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teach, container, false);

        tabLayout = view.findViewById(R.id.tl_categories);
        viewPager = view.findViewById(R.id.vp_content);

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        fragmentList.add(new VideoFragment("魔方", new ArrayList<>(), requestQueue));
        fragmentList.add(new VideoFragment("数独", new ArrayList<>(), requestQueue));
        fragmentList.add(new VideoFragment("围棋", new ArrayList<>(), requestQueue));
        titleList.add("魔方");
        titleList.add("数独");
        titleList.add("围棋");

        viewPager.getAdapter().notifyDataSetChanged();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}