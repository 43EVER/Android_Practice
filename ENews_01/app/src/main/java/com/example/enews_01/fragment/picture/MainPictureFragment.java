package com.example.enews_01.fragment.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.enews_01.R;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.fragment.news.NewsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainPictureFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    MainActivity activity;

    List<Fragment> fragmentList;
    List<String> titleList;

    public MainPictureFragment(MainActivity activity) {
        this.activity = activity;
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout = getView().findViewById(R.id.tl_indicator);
        viewPager = getView().findViewById(R.id.vp_content);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
        tabLayout.setupWithViewPager(viewPager, true);
        List<Integer> cidList = new ArrayList<>();
        titleList.addAll(Arrays.asList("风景", "动漫", "小清新", "宠物", "体育", "军事"));
        cidList.addAll(Arrays.asList(9, 26, 15, 14, 16, 22));
        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(new PictureFragment(cidList.get(i)));
            viewPager.getAdapter().notifyDataSetChanged();
        }

    }
}
