package com.example.rubikscube_test;

import android.os.Bundle;

import com.example.rubikscube_test.Fragment.ShowCubeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    public static final int SIZE = 3;
    public static final String FACES_ORDER = "UDFBLR"; //"FRBLUD";

    private String cubeString = "UUUUUUUUU" + "RRRRRRRRR" + "FFFFFFFFF"
            + "DDDDDDDDD" + "LLLLLLLLL" + "BBBBBBBBB";  //展开图中
    private String[] colorName =
            new String[]{0xFFFFFFFF+"", 0xFF00FF00+"", 0xFFFFA500+"",
                    0xFFFFFF00+"", 0xFF0000FF+"", 0xFFFF0000+""};

    public ViewPager viewPager;
    public FragmentStatePagerAdapter adapter;
    public List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
//        fragmentList.add(new LoadCubeFragment());
        fragmentList.add(new ShowCubeFragment());

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
