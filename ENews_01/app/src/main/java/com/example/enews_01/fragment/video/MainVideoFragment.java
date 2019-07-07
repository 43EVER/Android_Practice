package com.example.enews_01.fragment.video;

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
import com.example.enews_01.fragment.picture.PictureFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainVideoFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    MainActivity activity;

    List<Fragment> fragmentList;
    List<String> titleList;

    public MainVideoFragment(MainActivity activity) {
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
        List<String> cidList = new ArrayList<>();
        titleList.addAll(Arrays.asList("热点", "娱乐", "搞笑", "精品"));
        cidList.addAll(Arrays.asList("V9LG4B3A0", "V9LG4CHOR", "V9LG4E6VR", "00850FRB"));
        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(new VideoFragment(cidList.get(i)));
            viewPager.getAdapter().notifyDataSetChanged();
        }

    }

}
