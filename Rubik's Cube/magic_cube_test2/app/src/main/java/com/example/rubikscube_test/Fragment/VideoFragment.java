package com.example.rubikscube_test.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.example.rubikscube_test.Adapter.TeachItemAdapter;
import com.example.rubikscube_test.Data.TeachViewModel;
import com.example.rubikscube_test.HomeActivity;
import com.example.rubikscube_test.R;
import com.example.rubikscube_test.Utils.UpdateTeachTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoFragment extends Fragment {

    String category;
    Context context;
    List<TeachViewModel> data;
    RequestQueue requestQueue;

    public VideoFragment(String category, List<TeachViewModel> data, RequestQueue requestQueue, HomeActivity context) {
        this.category = category;
        data.forEach(e -> {
            if (e.getCategory().equals(category))
                this.data.add(e);
        });
        this.requestQueue = requestQueue;
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView rvContent = getView().findViewById(R.id.rv_content);
        TeachItemAdapter adapter = new TeachItemAdapter(data, (HomeActivity) context, requestQueue);
        rvContent.setLayoutManager(new LinearLayoutManager(context));
        rvContent.setAdapter(adapter);
        new UpdateTeachTask(adapter, requestQueue, category).execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_video, container, false);
        return view;
    }


}
