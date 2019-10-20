package com.example.rubikscube_test.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.catalinjurjiu.animcubeandroid.AnimCube;
import com.example.rubikscube_test.CubeSolver.LayerSolver;
import com.example.rubikscube_test.CubeSolver.min2phase.Search;
import com.example.rubikscube_test.MainActivity;
import com.example.rubikscube_test.R;
import com.example.rubikscube_test.Utils.ColorTransformer;

import androidx.fragment.app.Fragment;

import static com.catalinjurjiu.animcubeandroid.AnimCube.TAG;

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
    private CheckBox cfop;

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

                Log.e(TAG, "run: " + result);
                getActivity().runOnUiThread(() -> {
                    tv.setText(result);
                    animCube.setMoveSequence(result);
                    btnPre.setEnabled(true);
                    btnNext.setEnabled(true);
                });
            }
        }.start();

        cfop = (CheckBox) view.findViewById(R.id.cfop);

        cfop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... voids) {
                            LayerSolver solver = new LayerSolver(cubeString);
                            String res = solver.solve();
                            return res;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            animCube.setMoveSequence(s);
                            tv.setText(s);
                            animCube.setCubeModel(ColorTransformer.transCubestring(cubeString));
                        }
                    }.execute();
                }else{
                    animCube.setMoveSequence(result);
                    tv.setText(result);
                    animCube.setCubeModel(ColorTransformer.transCubestring(cubeString));
                }
            }
        });

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
