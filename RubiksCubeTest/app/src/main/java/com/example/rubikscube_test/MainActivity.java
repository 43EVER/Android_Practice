package com.example.rubikscube_test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int SIZE = 3;
    public static final String FACES_ORDER = "UDFBLR"; //"FRBLUD";

    private String cubeString = "UUUUUUUUU" + "RRRRRRRRR" + "FFFFFFFFF"
            + "DDDDDDDDD" + "LLLLLLLLL" + "BBBBBBBBB";  //展开图中0-5分别为URFDLB
    private String[] colorName =
            new String[]{0xFFFFFF00+"", 0xFFFFFFFF+"", 0xFF0000FF+"",
                    0xFF00FF00+"", 0xFFFFA500+"", 0xFFFF0000+""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new ShowCubeFragment(), "ShowCube")
                    .commit();
        }
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
