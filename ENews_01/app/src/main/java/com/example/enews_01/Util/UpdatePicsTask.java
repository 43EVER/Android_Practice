package com.example.enews_01.Util;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.enews_01.activity.MainActivity;
import com.example.enews_01.fragment.picture.PictureFragment;
import com.example.enews_01.model.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdatePicsTask extends AsyncTask<Integer, Void, Void> {

    PictureFragment pictureFragment;

    public UpdatePicsTask(PictureFragment fragment) {
        this.pictureFragment = fragment;
    }

    @Override
    protected Void doInBackground(final Integer... integers) {

        final StringRequest request = new StringRequest(Request.Method.GET,
                URLutil.getPictureRequestURL(integers[0], pictureFragment.pictureList.size(), 10),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(getClass().getName(), URLutil.getPictureRequestURL(integers[0], pictureFragment.pictureList.size(), 10));
                        Log.e(getClass().getName(), response);
                        try {
                            final List<Picture> pictureList = new ArrayList<>();
                            JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Picture picture = new Picture(jsonArray.getJSONObject(i));
                                pictureList.add(picture);
                            }
                            pictureFragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pictureFragment.adapter.notifyDataChangedAfterLoadMore(pictureList, true);
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
        ((MainActivity) pictureFragment.getActivity()).requestQueue.add(request);
        return null;
    }

}
