package com.example.rent.sdacourseapplication.books;


import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.sdacourseapplication.R;

import java.util.List;


public class BooksAdapter extends PagerAdapter{

    private List<Book> books;

    public BooksAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View pageLayout = inflater.inflate(R.layout.single_page_books, container, false);
        ImageView image = (ImageView) pageLayout.findViewById(R.id.books_image);
        image.setImageResource(books.get(position).getImageResourcesId());
        container.addView(pageLayout);

        return pageLayout;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
