package com.example.enews_01.fragment.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.enews_01.Adapter.PictureItemAdapter;
import com.example.enews_01.R;
import com.example.enews_01.Util.UpdatePicsTask;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class PictureFragment extends Fragment {

    public int cid;
    public PictureItemAdapter adapter;
    public RecyclerView rvPics;

    public List<Picture> pictureList;

    public PictureFragment(int cid) {
        this.cid = cid;
        pictureList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvPics = getView().findViewById(R.id.rv_pics);
        adapter = new PictureItemAdapter(pictureList, getActivity());
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);
        adapter.openLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new UpdatePicsTask(PictureFragment.this).execute(cid);
            }
        });
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ((MainActivity) getActivity()).openDetail(pictureList.get(i).getPic(), true);
            }
        });

        rvPics.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvPics.setAdapter(adapter);

        new UpdatePicsTask(this).execute(cid);
    }
}
