package com.example.enews_01.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.enews_01.R;
import com.example.enews_01.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tvTime = getView().findViewById(R.id.tv_time);
        TextView tvTmp = getView().findViewById(R.id.tv_tmp);
        TextView tvCity = getView().findViewById(R.id.tv_city);
        TextView tvDes = getView().findViewById(R.id.tv_des);

        String url = "https://free-api.heweather.net/s6/weather/now?location=auto_ip&key=2a07e26035fa41fdb7dd1bda662e16c3";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONArray("HeWeather6").getJSONObject(0);
                            String status = object.getString("status");
                            if (status.equals("ok")) {
                                JSONObject basic = object.getJSONObject("basic");
                                JSONObject update = object.getJSONObject("update");
                                JSONObject weather = object.getJSONObject("now");
                                tvCity.setText(basic.getString("location"));
                                tvTime.setText(update.getString("loc"));
                                tvTmp.setText(weather.getString("tmp") + "℃");
                                tvDes.setText(weather.getString("cond_txt"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(getClass().getName() + ":updateWeather()", error.toString());
                    }
                });
        ((MainActivity) getActivity()).requestQueue.add(request);
    }
}
