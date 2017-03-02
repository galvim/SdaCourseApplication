package com.example.rent.sdacourseapplication.books;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {

    private int id;
    private boolean isRead;
    private String title;

    @DrawableRes
    private int imageResourcesId;

    public Book(int id, int imageResourcesId, String title) {
        this.id = id;
        this.title = title;
        this.imageResourcesId = imageResourcesId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResourcesId() {
        return imageResourcesId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
