package com.example.rubikscube_test.Adapter;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.rubikscube_test.Data.TeachViewModel;
import com.example.rubikscube_test.HomeActivity;
import com.example.rubikscube_test.R;

import java.util.List;

import androidx.annotation.NonNull;
import cn.jzvd.JzvdStd;

public class TeachItemAdapter extends BaseQuickAdapter<TeachViewModel, BaseViewHolder> {

    List<TeachViewModel> data;
    HomeActivity context;

    public TeachItemAdapter(List<TeachViewModel> data, HomeActivity context, RequestQueue requestQueue) {
        super(R.layout.teach_item, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TeachViewModel item) {
        JzvdStd video = helper.getView(R.id.vd_content);
        video.setUp(item.getUrl(), item.getTitle());
        if (item.getTitle().contains("魔方"))
            Glide.with(context).load(R.drawable.cube).into(video.thumbImageView);
        else if (item.getTitle().contains("数独"))
            Glide.with(context).load(R.drawable.sudoku).into(video.thumbImageView);
        else
            Glide.with(context).load(R.drawable.go).into(video.thumbImageView);
    }

}