package com.example.enews_01.Util;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.fragment.news.NewsFragment;
import com.example.enews_01.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpdateNewsTask extends AsyncTask<String, Void, Void> {

    NewsFragment newsFragment;

    public UpdateNewsTask(NewsFragment fragment) {
        this.newsFragment = fragment;
    }

    @Override
    protected Void doInBackground(final String... strings) {

        final StringRequest request = new StringRequest(Request.Method.GET, URLutil.getNewsRequestURL(strings[0], newsFragment.newsList.size(), 10),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), URLutil.getNewsRequestURL(strings[0], newsFragment.newsList.size(), 10));
                        Log.e(getClass().getName(), response);
                        try {
                            JSONArray jsonArray = new JSONObject(response).getJSONObject("result").getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                News news = new News(jsonArray.getJSONObject(i));
                                newsFragment.newsList.add(news);
                            }
                            newsFragment.adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        newsFragment.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                newsFragment.adapter.notifyDataChangedAfterLoadMore(true);
                                List<String> tips = new ArrayList<>();
                                List<News> newsList = new ArrayList<>();
                                Random random = new Random();
                                for (int i = 0; i < 3; i++) {
                                    newsList.add(newsFragment.newsList.get(random.nextInt(newsFragment.newsList.size())));
                                    tips.add(newsList.get(i).getTitle());
                                }
                                newsFragment.banner.setData(newsList, tips);
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getName(), error.toString());
                    }
                });
        ((MainActivity) (newsFragment.getActivity())).requestQueue.add(request);
        return null;
    }
}
