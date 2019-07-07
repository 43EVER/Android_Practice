package com.example.enews_01.Adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.enews_01.R;
import com.example.enews_01.model.Video;

import java.util.List;

import cn.jzvd.JzvdStd;

public class VideoItemAdapter extends BaseQuickAdapter<Video> {

    Context context;

    public VideoItemAdapter(List<Video> data, Context context) {
        super(R.layout.item_video, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Video video) {
        JzvdStd vd = baseViewHolder.getView(R.id.vd_content);
        vd.setUp(video.getUrl(), video.getTitle());
        Glide.with(context)
                .load(video.getPic())
                .into(vd.thumbImageView);
    }
}
