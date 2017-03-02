package com.example.rent.sdacourseapplication.books;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {

    @DrawableRes
    private int imageResourcesId;

    public Book(int imageResourcesId) {
        this.imageResourcesId = imageResourcesId;
    }

    @DrawableRes
    public int getImageResourcesId() {
        return imageResourcesId;
    }
}
