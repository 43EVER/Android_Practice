package com.example.rubikscube_test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rubikscube_test.Fragment.ShowCubeFragment;
import com.example.rubikscube_test.Fragment.WebviewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int SIZE = 3;
    public static final String FACES_ORDER = "UDFBLR"; //"FRBLUD";

    private String cubeString = "UUUUUUUUU" + "RRRRRRRRR" + "FFFFFFFFF"
            + "DDDDDDDDD" + "LLLLLLLLL" + "BBBBBBBBB";  //展开图中0-5分别为URFDLB
    private String[] colorName =
            new String[]{0xFFFFFF00+"", 0xFFFFFFFF+"", 0xFF0000FF+"",
                    0xFF00FF00+"", 0xFFFFA500+"", 0xFFFF0000+""};

    public ViewPager viewPager;
    public FragmentStatePagerAdapter adapter;
    public List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fragmentList.add(new ShowCubeFragment());
        fragmentList.add(new WebviewFragment());

        viewPager = findViewById(R.id.viewpager);
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
        };
        viewPager.setAdapter(adapter);
    }

    public void setColorName(String[] colorName) {
        this.colorName = colorName;
    }

    public void setCubeString(String cubeString) {
        this.cubeString = cubeString;
    }

    public String[] getColorName() {
        return colorName;
    }

    public String getCubeString() {
        return cubeString;
    }

}
