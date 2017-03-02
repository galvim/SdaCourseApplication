package com.example.rent.sdacourseapplication.books;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
                tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Book book = new Book(1,R.drawable.book1, "clean code");
        book.setRead(sharedPreferences.getBoolean(String.valueOf(book.getId()), false));
        Book book2 = new Book(2, R.drawable.book2, "effective java");
        book2.setRead(sharedPreferences.getBoolean(String.valueOf(book2.getId()), false));
        Book book3 = new Book(3, R.drawable.book3, "pattern design");
        book3.setRead(sharedPreferences.getBoolean(String.valueOf(book3.getId()), false));
        List<Book> list = Arrays.asList(book,book2,book3);
               BooksAdapter adapter = new BooksAdapter(list, sharedPreferences);
                viewPager.setAdapter(adapter);
    }
}
