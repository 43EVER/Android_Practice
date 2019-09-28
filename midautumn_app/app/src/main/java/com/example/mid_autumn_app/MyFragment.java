package com.example.mid_autumn_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cunoraz.gifview.library.GifView;

public class MyFragment extends Fragment {

    private int gifres;
    private GifView gifView;

    public MyFragment(int gifres) {
        this.gifres = gifres;
    }

    public void clickPlay() {
        gifView.play();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gifView = view.findViewById(R.id.gif_view);
        gifView.setGifResource(gifres);
    }
}
