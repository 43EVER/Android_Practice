package com.example.enews_01.model;

import org.json.JSONException;
import org.json.JSONObject;

public class News {

    private String title;
    private String time;
    private String src;
    private String pic;
    private String url;
    private String content;

    public News(JSONObject object) {
        try {
            this.title = object.getString("title");
            this.time = object.getString("time");
            this.src = object.getString("src");
            this.pic = object.getString("pic");
            this.url = object.getString("url");
            this.content = object.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getSrc() {
        return src;
    }

    public String getPic() {
        return pic;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}