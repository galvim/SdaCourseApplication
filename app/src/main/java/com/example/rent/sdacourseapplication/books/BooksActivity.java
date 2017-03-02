package com.example.rent.sdacourseapplication.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-03-02.
 */

public class BooksActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.books_viewpager);
        Book book = new Book(R.drawable.book1);
        Book book2 = new Book(R.drawable.book2);
        Book book3 = new Book(R.drawable.book3);
        List<Book> list = Arrays.asList(book,book2,book3);
        BooksAdapter adapter = new BooksAdapter(list);
        viewPager.setAdapter(adapter);
    }
}
