package com.example.rent.sdacourseapplication.books;


import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.rent.sdacourseapplication.R;

import java.util.List;


public class BooksAdapter extends PagerAdapter{

    private List<Book> books;
    private SharedPreferences sharedPreferences;

    public BooksAdapter(List<Book> books,SharedPreferences  sharedPreferences) {
        this.books = books;
        this.sharedPreferences = sharedPreferences;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View pageLayout = inflater.inflate(R.layout.single_page_books, container, false);
        ImageView image = (ImageView) pageLayout.findViewById(R.id.books_image);
        image.setImageResource(books.get(position).getImageResourcesId());

        CheckBox checkBox = (CheckBox) pageLayout.findViewById(R.id.isRead);
                checkBox.setOnCheckedChangeListener(null);
                checkBox.setChecked(books.get(position).isRead());
               checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
               {

                                @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    Book book = books.get(position);
                                    book.setRead(isChecked);

                                    sharedPreferences
                                            .edit()
                                            .putBoolean(String.valueOf(book.getId()),book.isRead())
                                            .apply();
                                books.get(position).setRead(isChecked);
                            }
                   });



        container.addView(pageLayout);

        return pageLayout;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
        public CharSequence getPageTitle(int position) {
                return books.get(position).getTitle();
           }
}
