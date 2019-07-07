package com.example.enews_01.Util;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.fragment.video.VideoFragment;
import com.example.enews_01.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateVideoTask extends AsyncTask<String, Void, Void> {

    VideoFragment videoFragment;

    public UpdateVideoTask(VideoFragment fragment) {
        this.videoFragment = fragment;
    }

    @Override
    protected Void doInBackground(String... strings) {

        StringRequest request = new StringRequest(Request.Method.GET, URLutil.getVideoRequestURL(strings[0], videoFragment.videoList.size(), 10),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), URLutil.getVideoRequestURL(strings[0], videoFragment.videoList.size(), 10));
                        Log.e(getClass().getName(), response);
                        try {
                            List<Video> videoList = new ArrayList<>();
                            JSONArray rootAyyar = new JSONObject(response).getJSONArray(strings[0]);
                            for (int i = 0; i < rootAyyar.length(); i++) {
                                JSONObject jsonObject = rootAyyar.getJSONObject(i);
                                Video video = new Video(rootAyyar.getJSONObject(i));
                                videoList.add(video);
                            }
                            videoFragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    videoFragment.adapter.notifyDataChangedAfterLoadMore(videoList, true);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getName(), error.toString());
                    }
                });
        ((MainActivity) videoFragment.getActivity()).requestQueue.add(request);

        return null;
    }



}
