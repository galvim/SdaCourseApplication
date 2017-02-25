package com.example.rent.sdacourseapplication.milionQuiz;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.rent.sdacourseapplication.R.layout.quiz_activity;

public class QuizActivity extends AppCompatActivity {

    public QuizActivity() throws IOException {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
       ValueAnimator objectAnimator =  ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(1000);
               objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.start();

        String json = null;
        try {
            json = loadQuizJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String loadQuizJson() throws IOException{

            StringBuilder buf = new StringBuilder();
            InputStream json = getAssets().open("quiz_data.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine() ) != null) {
                buf.append(str);
                in.close();


        return buf.toString();
    }





}
