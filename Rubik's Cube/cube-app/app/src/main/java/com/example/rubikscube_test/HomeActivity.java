package com.example.rubikscube_test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rubikscube_test.Fragment.GameCenterFragment;
import com.example.rubikscube_test.Fragment.TeachFragment;
import com.example.rubikscube_test.Fragment.WebviewFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public ViewPager viewPager;
    public FragmentStatePagerAdapter adapter;
    public List<Fragment> fragmentList;
    public List<String> titleList;
    public RequestQueue requestQueue;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestPermissions();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        fragmentList = new ArrayList<>();
        fragmentList.add(new GameCenterFragment());
        fragmentList.add(new TeachFragment(this));
        fragmentList.add(new WebviewFragment());
        fragmentList.add(new WebviewFragment());

        titleList = new ArrayList<>();
        titleList.add("游戏");
        titleList.add("教学");
        titleList.add("论坛");
        titleList.add("我的");

        TabLayout tabLayout = findViewById(R.id.nav);
        viewPager = findViewById(R.id.home);



        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return PagerAdapter.POSITION_NONE;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if(cn.jzvd.JzvdStd.backPress()){
            return;
        }
        super.onBackPressed();
    }

    private void requestPermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "识别魔方需要使用相机", 0, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Toast.makeText(this, "")
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
