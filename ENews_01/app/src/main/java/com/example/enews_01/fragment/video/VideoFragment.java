package com.example.enews_01.fragment.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.enews_01.Adapter.VideoItemAdapter;
import com.example.enews_01.R;
import com.example.enews_01.Util.UpdateVideoTask;
import com.example.enews_01.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    public String id;
    public VideoItemAdapter adapter;
    public RecyclerView rvVideos;

    public List<Video> videoList;

    public VideoFragment(String id) {
        this.id = id;
        videoList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvVideos = getView().findViewById(R.id.rv_videos);
        adapter = new VideoItemAdapter(videoList, getActivity());
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);
        adapter.openLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new UpdateVideoTask(VideoFragment.this).execute(id);
            }
        });

        rvVideos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvVideos.setAdapter(adapter);

        new UpdateVideoTask(this).execute(id);
    }
}
