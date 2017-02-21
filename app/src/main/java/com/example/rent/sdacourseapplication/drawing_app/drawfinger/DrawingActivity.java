package com.example.rent.sdacourseapplication.drawing_app.drawfinger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.gallery.GalleryActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawingActivity extends AppCompatActivity {
    public static final String DRAWING_GALLERY_DIR = "drawing_gallery";
    private SimpleDrawingView simpleDrawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_app_activity_main);


        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);
        Button clearButton = (Button) findViewById(R.id.clear_view);
        Button blueButton = (Button) findViewById(R.id.blue_button);
        Button redButton = (Button) findViewById(R.id.red_button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.clear();
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingActivity.this, R.color.blue));
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawingActivity.this, R.color.red));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pod,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.clear_view){
            simpleDrawingView.clear();
        } else if(item.getItemId() == R.id.save) {
            try {
                saveDrawingToFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (item.getItemId() == R.id.drawing_gallery){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile() throws IOException {
        File drawingFile = new File(getDrawingGalleryDirectory(),createFileName());
       FileOutputStream fileOutputStream =  new FileOutputStream(drawingFile);
        Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

    }

    private String createFileName() {
        String timeStamp =  new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());

        return "my_drawing" + timeStamp+".png";
    }

    private File getDrawingGalleryDirectory(){
       return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private Bitmap convertViewToBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }
}