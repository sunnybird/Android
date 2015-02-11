package com.example.jinlong.dailyartical.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by JinLong on 2015/2/8.
 */
public class Comment extends BmobObject {

    private String content;
    private String time;
    private String user;
    private int favour;

    public Comment(){

    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFavour() {
        return favour;
    }

    public void setFavour(int favour) {
        this.favour = favour;
    }

    public Comment(String content, String user,String time,  int favour) {
        super();
        this.content = content;
        this.time = time;
        this.user = user;
        this.favour = favour;
    }

}
