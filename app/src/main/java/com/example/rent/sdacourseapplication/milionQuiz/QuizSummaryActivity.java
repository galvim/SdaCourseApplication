package com.example.rent.sdacourseapplication.milionQuiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;

import org.w3c.dom.Text;

public class QuizSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_summary);

        TextView resultOK = (TextView) findViewById(R.id.result_OK);
        TextView resultNotOk = (TextView) findViewById(R.id.result_not_OK);

        resultOK.setText("Poprawna odpowiedź" + getIntent().getIntExtra(QuizActivity.CORRECT_ANSWERS, 0));
        resultNotOk.setText("Niepooprawna odpowiedź" + getIntent().getIntExtra(QuizActivity.INCORRECT_ANSWERS, 0));

        Button ponow = (Button) findViewById(R.id.button_reload);
        ponow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuizSummaryActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
