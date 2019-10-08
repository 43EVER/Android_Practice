package com.example.rubikscube_test.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.catalinjurjiu.animcubeandroid.AnimCube;
import com.example.rubikscube_test.MainActivity;
import com.example.rubikscube_test.R;
import com.example.rubikscube_test.Utils.ColorTransformer;

import min2phase.Search;

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
    AnimCube animCube;

    Button btnPre, btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_cube, container, false);

        tv = view.findViewById(R.id.textView2);
        animCube = view.findViewById(R.id.animcube);

        Button btnLoadCube = view.findViewById(R.id.btn_load_cube);
        btnLoadCube.setOnClickListener(this);

        btnPre = view.findViewById(R.id.btn_pre_step);
        btnPre.setOnClickListener(this);

        btnNext = view.findViewById(R.id.btn_next_step);
        btnNext.setOnClickListener(this);

        drawCube();

        new Thread() {
            @Override
            public void run() {
                //Error 8 probeMax 100 -> 1000
                result = search.solution(cubeString, maxDepth, 1000, 0, mask);
                if (result == null || result.isEmpty()) {
                    return;
                }
                getActivity().runOnUiThread(() -> {
                    tv.setText(result);
                    animCube.setMoveSequence(result);
                    btnPre.setEnabled(true);
                    btnNext.setEnabled(true);
                });
            }
        }.start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        drawCube();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_cube:
                ((MainActivity) getActivity()).fragmentList.set(0, new LoadCubeFragment());
                ((MainActivity) getActivity()).adapter.notifyDataSetChanged();
                break;
            case R.id.btn_pre_step:
                animCube.animateMoveReversed();
                break;
            case R.id.btn_next_step:
                animCube.animateMove();
                break;
            default:
                break;
        }
    }

    private void drawCube() {
        colorName = ((MainActivity) getActivity()).getColorName();
        cubeString = ((MainActivity) getActivity()).getCubeString();

        animCube.setCubeModel(ColorTransformer.transCubestring(cubeString));
    }
}
