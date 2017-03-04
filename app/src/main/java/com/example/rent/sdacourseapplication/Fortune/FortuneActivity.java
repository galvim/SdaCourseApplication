package com.example.rent.sdacourseapplication.Fortune;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;
import com.squareup.seismic.ShakeDetector;

import java.util.Random;

public class FortuneActivity extends AppCompatActivity implements ShakeDetector.Listener{

    private FrameLayout layer;
    private FrameLayout parentLayer;
    private Random random = new Random();
    private String[] tab = {"Masz raka ", " Jesz mięso w piątek", "Nie masz nic","Jutro umrzesz","wygrałeś milion"};
    private Animator hidingAnimator;
    private Animator showingAnimator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortune_activity);

        TextView text2 =  (TextView) findViewById(R.id.text_2);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        layer = (FrameLayout) findViewById(R.id.frame_2);
        parentLayer = (FrameLayout) findViewById(R.id.frame_1);

        parentLayer.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    animateCircuralReveal((int)event.getX(),(int)event.getY(), layer);
                }
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateCircuralReveal(int x, int y, final FrameLayout layer) {
        if((hidingAnimator != null && hidingAnimator.isRunning()) || (showingAnimator !=null && showingAnimator.isRunning())){
            return;
        }
        if (layer.getVisibility() == View.VISIBLE) {
            hidingAnimator = ViewAnimationUtils
                    .createCircularReveal(layer, x,
                   y, layer.getHeight(), 0);
            hidingAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            hidingAnimator.start();
        } else {
            int color = Color.argb(255,random.nextInt(255) ,random.nextInt(255),random.nextInt(255)  );
            layer.setBackgroundColor(color);
            showingAnimator = ViewAnimationUtils
                    .createCircularReveal(layer, x,
                    y,0, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            showingAnimator.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void hearShake() {

        animateCircuralReveal(random.nextInt(layer.getWidth()),random.nextInt(layer.getHeight()), layer);
        TextView text2 =  (TextView) findViewById(R.id.text_2);
        text2.setText(tab[random.nextInt(5)]);
    }
}
