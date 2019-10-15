package com.example.rubikscube_test.Data;

import org.json.JSONException;
import org.json.JSONObject;

public class TeachViewModel {

    private String title;
    private String url;
    private String category;

    public TeachViewModel(JSONObject jsonObject) {
        try {
            this.title = jsonObject.getString("title");
            this.url = jsonObject.getString("url");
            this.category = jsonObject.getString("category");
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

    public String getCategory() {
        return category;
    }
}
