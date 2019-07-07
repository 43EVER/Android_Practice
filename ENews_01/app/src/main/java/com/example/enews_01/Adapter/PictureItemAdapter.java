package com.example.enews_01.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.enews_01.R;
import com.example.enews_01.model.Picture;

import java.util.List;

public class PictureItemAdapter extends BaseQuickAdapter<Picture> {

    Context context;

    public PictureItemAdapter(List<Picture> data, Context context) {
        super(R.layout.item_picture, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Picture picture) {
        Glide.with(context)
                .load(picture.getPic())
                .into((ImageView) baseViewHolder.getView(R.id.iv_content));
    }
}
