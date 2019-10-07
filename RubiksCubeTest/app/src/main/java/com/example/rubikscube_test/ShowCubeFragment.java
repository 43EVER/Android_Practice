package com.example.rubikscube_test;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import min2phase.Search;

import static com.example.rubikscube_test.ColorDetector.nameToRGB;

/**
 * Created by tarjan on 17-3-11.
 */

public class ShowCubeFragment extends Fragment implements View.OnClickListener {
    private String cubeString;
    private String result;
    private String[] colorName;
    private Search search = new Search();
    private int maxDepth = 21;
    private int mask = 0;

    TextView tv;
    TextView[] cubePieceTextView = new TextView[54];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_cube, container, false);

        tv = view.findViewById(R.id.textView2);

        Button btnLoadCube = view.findViewById(R.id.btn_load_cube);
        btnLoadCube.setOnClickListener(this);

        initDrawCube(view);
        drawCube();

        new Thread() {
            @Override
            public void run() {
                //Error 8 probeMax 100 -> 1000
                result = search.solution(cubeString, maxDepth, 1000, 0, mask);
                tv.post(() -> tv.setText("Solution:\n" + result));
            }
        }.start();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_cube:
                getFragmentManager()
                        .beginTransaction()
                        .hide(this)
                        .add(R.id.container, new LoadCubeFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }
    }

    private void initDrawCube(View view) {
        int s = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                22, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(s, s);
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics());
        lp.setMargins(margin, margin, margin, margin);

        for (int i = 0; i < 6; i++) {
            Resources res = getResources();
            String idName = "cubeFace" + i;
            int id = res.getIdentifier(idName, "id", getActivity().getPackageName());
            LinearLayout cf = view.findViewById(id);

            for (int j = 0; j < MainActivity.SIZE; j++) {
                LinearLayout l = new LinearLayout(view.getContext());
                l.setOrientation(LinearLayout.HORIZONTAL);
                for (int k = 0; k < MainActivity.SIZE; k++) {
                    TextView tv = new TextView(view.getContext());
                    tv.setLayoutParams(lp);
                    l.addView(tv);
                    cubePieceTextView[i * 9 + j * 3 + k] = tv;
                }
                cf.addView(l);
            }
        }
    }

    private void drawCube() {
        colorName = ((MainActivity) getActivity()).getColorName();
        cubeString = ((MainActivity) getActivity()).getCubeString();

        for (int i = 0; i < 54; i++) {
            String color = colorName[MainActivity.FACES_ORDER.indexOf(cubeString.charAt(i))];
            cubePieceTextView[i].setBackgroundColor(nameToRGB(color));
        }
    }
}
