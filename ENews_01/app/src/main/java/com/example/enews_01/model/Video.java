package com.example.enews_01.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {

    public String url;
    public String title;
    public String pic;

    public Video(JSONObject object) {
        try {
            title = object.getString("title");
            url = object.getString("mp4_url");
            pic = object.getString("cover");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getPic() {
        return pic;
    }

}
