package com.example.enews_01.fragment.news;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.enews_01.Adapter.NewsItemAdapter;
import com.example.enews_01.R;
import com.example.enews_01.Util.UpdateNewsTask;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.model.News;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class NewsFragment extends Fragment {

    public String channel;
    public NewsItemAdapter adapter;
    public BGABanner banner;
    public RecyclerView rvNews;

    public List<News> newsList;

    public NewsFragment(String channel) {
        this.channel = channel;
        newsList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvNews = getView().findViewById(R.id.rv_news);
        adapter = new NewsItemAdapter(newsList, getActivity());
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);
        adapter.openLoadMore(1, true);

        View view = View.inflate(getContext(), R.layout.header_news, null);
        banner = view.findViewById(R.id.banner);
        banner.setAdapter(new BGABanner.Adapter<ImageView, News>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable News model, int position) {
                Glide.with(getContext())
                        .load(model.getPic())
                        .into(itemView);
            }
        });
        banner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                News news = (News) model;
                ((MainActivity) getActivity()).openDetail(news.getContent(), false);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new UpdateNewsTask(NewsFragment.this).execute(channel);
            }
        });
        adapter.addHeaderView(view);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ((MainActivity) getActivity()).openDetail(newsList.get(i).getContent(), false);
            }
        });

        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setAdapter(adapter);

        new UpdateNewsTask(this).execute(channel);
    }
}