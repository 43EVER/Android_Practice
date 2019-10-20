package com.example.rubikscube_test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.rubikscube_test.GameWindow;
import com.example.rubikscube_test.MainActivity;
import com.example.rubikscube_test.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameCenterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameCenterFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View view;

    private Button start_game,order_2,order_3,order_4,get_photo,
            sudoku_order8,sudoku_order11,sudoku_order13,
            magic_cube_back,sududo_back;
    private LinearLayout game_menu,magic_cube_mode,sudoku_mode,magic_cube,sudoku,id3,id4;
    private BGABanner banner;

    public GameCenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameCenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameCenterFragment newInstance(String param1, String param2) {
        GameCenterFragment fragment = new GameCenterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_game_center, container, false);

        order_2 = (Button) view.findViewById(R.id.order2);
        order_3 = (Button) view.findViewById(R.id.order3);
        order_4 = (Button) view.findViewById(R.id.order4);
        get_photo = (Button) view.findViewById(R.id.get_photo);
        game_menu = (LinearLayout) view.findViewById(R.id.game_menu);
        magic_cube = (LinearLayout) view.findViewById(R.id.magic_cube);
        sudoku = (LinearLayout) view.findViewById(R.id.sudoku);
        magic_cube_mode = (LinearLayout) view.findViewById(R.id.magic_cube_mode);
        sudoku_mode = (LinearLayout) view.findViewById(R.id.sudoku_mode);
        sududo_back = (Button) view.findViewById(R.id.sudoku_back);
        magic_cube_back  = (Button) view.findViewById(R.id.magic_cube_back);
        sudoku_order8 = (Button) view.findViewById(R.id.sudoku_order8);
        sudoku_order11 = (Button) view.findViewById(R.id.sudoku_order11);
        sudoku_order13 = (Button) view.findViewById(R.id.sudoku_order13);
        id3 = (LinearLayout) view.findViewById(R.id.id3);
        id4 = (LinearLayout) view.findViewById(R.id.id4);
        banner = view.findViewById(R.id.banner);

        get_photo.setOnClickListener(this);
        order_2.setOnClickListener(this);
        order_3.setOnClickListener(this);
        order_4.setOnClickListener(this);
        magic_cube.setOnClickListener(this);
        sudoku.setOnClickListener(this);
        magic_cube_back.setOnClickListener(this);
        sududo_back.setOnClickListener(this);
        sudoku_order8.setOnClickListener(this);
        sudoku_order11.setOnClickListener(this);
        sudoku_order13.setOnClickListener(this);
        id3.setOnClickListener(this);
        id4.setOnClickListener(this);

        banner = view.findViewById(R.id.banner);
        banner.setAdapter((BGABanner.Adapter<ImageView, Integer>) (banner, itemView, model, position) -> Glide.with(getContext())
                .load(model)
                .fitCenter()
                .into(itemView));

        List<Integer> imgs = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        imgs.add(R.drawable.lb1);
        imgs.add(R.drawable.lb2);
        banner.setData(imgs, titles);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sudoku_order8:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","7");
                startActivity(intent);
                break;
            }
            case R.id.sudoku_order11:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","10");
                startActivity(intent);
                break;
            }
            case R.id.sudoku_order13:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","13");
                startActivity(intent);
                break;
            }
            case R.id.order3:{
                Intent intent = new Intent(view.getContext(), GameWindow.class);
                intent.putExtra("order","3");
                startActivity(intent);
                break;
            }
            case R.id.order4:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","4");
                startActivity(intent);
                break;
            }
            case R.id.order2:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","2");
                startActivity(intent);
                break;
            }
            case R.id.get_photo:{
                startActivity(new Intent(view.getContext(), MainActivity.class));
                break;
            }
            case R.id.magic_cube:{
                magic_cube_back.setVisibility(View.VISIBLE);
                game_menu.setVisibility(View.GONE);
                magic_cube_mode.setVisibility(View.VISIBLE);
                sudoku_mode.setVisibility(View.GONE);
                break;
            }
            case R.id.sudoku:{
                sududo_back.setVisibility(View.VISIBLE);
                game_menu.setVisibility(View.GONE);
                magic_cube_mode.setVisibility(View.GONE);
                sudoku_mode.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.id3:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","30");
                startActivity(intent);
                break;
            }
            case R.id.id4:{
                Intent intent = new Intent(view.getContext(),GameWindow.class);
                intent.putExtra("order","40");
                startActivity(intent);
                break;
            }
            case R.id.magic_cube_back:{
                magic_cube_mode.setVisibility(View.GONE);
                game_menu.setVisibility(View.VISIBLE);
                magic_cube_back.setVisibility(View.GONE);
                break;
            }
            case R.id.sudoku_back:{
                sudoku_mode.setVisibility(View.GONE);
                game_menu.setVisibility(View.VISIBLE);
                sudoku_mode.setVisibility(View.GONE);
                break;
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
