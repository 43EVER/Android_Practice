package com.example.rubikscube_test.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.example.rubikscube_test.Adapter.TeachItemAdapter;
import com.example.rubikscube_test.Data.TeachViewModel;

import org.json.JSONArray;
import org.json.JSONException;

public class UpdateTeachTask extends AsyncTask<Void, Void, Void> {

    TeachItemAdapter adapter;
    RequestQueue requestQueue;
    String category;

    public UpdateTeachTask(TeachItemAdapter adapter, RequestQueue requestQueue, String category) {
        this.adapter = adapter;
        this.requestQueue = requestQueue;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringRequest request = new StringRequest("http://imust-s.com/api/GetAllVideoURL",
                response -> {
                    try {
                        Log.e(getClass().getName(), response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                if (jsonArray.getJSONObject(i).getString("category").equals(category)) {
                                    adapter.addData(new TeachViewModel(jsonArray.getJSONObject(i)));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    Log.e(getClass().getName(), error.toString());
                }
        );
        requestQueue.add(request);
        return null;
    }

}
