package com.example.enews_01.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Picture {

    String pic;

    public Picture(JSONObject object) {
        try {
            pic = object.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPic() {
        return pic;
    }
}
