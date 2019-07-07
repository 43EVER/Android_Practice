package com.example.enews_01.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.enews_01.R;
import com.example.enews_01.model.News;

import java.util.List;

public class NewsItemAdapter extends BaseQuickAdapter<News> {

    Context context;

    public NewsItemAdapter(List<News> data, Context context) {
        super(R.layout.item_news, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        baseViewHolder.setText(R.id.tv_content, news.getTitle());
        Glide.with(context)
                .load(news.getPic())
                .into((ImageView) baseViewHolder.getView(R.id.iv_content));
    }
}
