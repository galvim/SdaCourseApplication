package com.example.rent.sdacourseapplication.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.drawing_app.drawfinger.DrawingActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GalleryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_Pager);

        File dir = getExternalFilesDir(DrawingActivity.DRAWING_GALLERY_DIR);
        File[] files  = dir.listFiles();
        viewPager.setAdapter(new DrawingPagerAdapter(files));

    }
}
