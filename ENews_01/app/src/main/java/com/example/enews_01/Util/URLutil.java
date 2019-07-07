package com.example.enews_01.Util;

public class URLutil {

    public static String getNewsRequestURL(String channel, int start, int num) {
        return "https://api.jisuapi.com/news/get?" + "channel=" + channel + "&start=" + start + "&num=" + num + "&appkey=d1c3d27d40fb469d";
    }

    public static String getPictureRequestURL(int cid, int start, int num) {
        return "http://wallpaper.apc.360.cn/index.php?%20c=WallPaper&a=getAppsByCategory&cid=" + cid + "&start=" +  start + "&count=" + num;
    }

    public static String getVideoRequestURL(String id, int start, int num) {
        return "http://c.m.163.com/nc/video/list/" + id + "/n/" + start + "-" + num + ".html";
    }

}
