package com.example.jinlong.dailyartical.bean;

import java.io.Serializable;

/**
 * Created by JinLong on 2015/2/8.
 */
public class Artical implements Serializable {


    private String Title;

    public Artical(){

    }
    public Artical(String title, String author, String content) {
        super();
        Title = title;
        Author = author;
        Content = content;
        int num = (int) (Math.random() * 99 + 1);
        ImageUri = "http://meiriyiwen.com/images/new_feed/bg_" + num + ".jpg";
    }

    private String Author;
    private String Content;
    private String ImageUri;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }
}
