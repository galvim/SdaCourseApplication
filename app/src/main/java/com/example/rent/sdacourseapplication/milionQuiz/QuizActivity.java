package com.example.rent.sdacourseapplication.milionQuiz;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacourseapplication.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static android.R.attr.animation;
import static com.example.rent.sdacourseapplication.R.layout.quiz_activity;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String INDEX_KEY = "index_key";

    private int currentQuestionIndex;
    private QuizContainer quizContainer;
    private boolean wasViewClicked;
    private ValueAnimator objectAnimator;
    public static final String CORRECT_ANSWERS = "correct answers";
    public static final String INCORRECT_ANSWERS = "incorrect answers";

    private int correctAnswers;
    private int incorrectAnswers;

    public QuizActivity() throws IOException {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        currentQuestionIndex = getIntent().getIntExtra(INDEX_KEY, 0);

        incorrectAnswers = getIntent().getIntExtra(INCORRECT_ANSWERS,0);


        String json = null;
        try {
            json = loadQuizJson();
            quizContainer = new Gson().fromJson(json, QuizContainer.class);

            if (currentQuestionIndex <= quizContainer.getQuestionsCount()-1) {
                currentQuestionIndex = getIntent().getIntExtra(INDEX_KEY, 0);
            } else {
                Toast.makeText(QuizActivity.this," KONIEC PYTA", Toast.LENGTH_SHORT);
            }
            TextView question_textView = (TextView) findViewById(R.id.question_textView);
            question_textView.setText(quizContainer.getQuestions().get(currentQuestionIndex).getQuestion());

            TextView odp1 = (TextView) findViewById(R.id.odp1);
            TextView odp2 = (TextView) findViewById(R.id.odp2);
            TextView odp3 = (TextView) findViewById(R.id.odp3);
            TextView odp4 = (TextView) findViewById(R.id.odp4);

            odp1.setText(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(0).getText());
            odp1.setTag(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(0).isCorrect());
            odp2.setText(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(1).getText());
            odp2.setTag(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(1).isCorrect());
            odp3.setText(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(2).getText());
            odp3.setTag(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(2).isCorrect());
            odp4.setText(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(3).getText());
            odp4.setTag(quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(3).isCorrect());

            odp1.setOnClickListener(this);
            odp2.setOnClickListener(this);
            odp3.setOnClickListener(this);
            odp4.setOnClickListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(1000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
       // objectAnimator.addListener((AnimatorListenerAdapter) progressBar.onAnimationEnd(animation));
        objectAnimator.start();


    }


    private String loadQuizJson() throws IOException {

        StringBuilder buf = new StringBuilder();
        InputStream json = getAssets().open("quiz_data.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();
        return buf.toString();
    }

    @Override
    public void onClick(View v) {
        if (!wasViewClicked) {

            if ((Boolean) v.getTag()) {
                ++correctAnswers;
                Toast.makeText(v.getContext(), "Odpowiedz poprawna", Toast.LENGTH_SHORT).show();

            } else {
                ++incorrectAnswers;
                Toast.makeText(v.getContext(), "Zła odpowiedź", Toast.LENGTH_SHORT).show();
            }
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (currentQuestionIndex < quizContainer.getQuestionsCount() -1) {
                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                    intent.putExtra(INDEX_KEY, ++currentQuestionIndex);
                        intent.putExtra(CORRECT_ANSWERS, correctAnswers);
                        intent.putExtra(INCORRECT_ANSWERS, incorrectAnswers);
                    startActivity(intent);
                } else {
                        Intent intent = new Intent(QuizActivity.this, QuizSummaryActivity.class);
                        intent.putExtra(CORRECT_ANSWERS, correctAnswers);
                        intent.putExtra(INCORRECT_ANSWERS, incorrectAnswers);
                        startActivity(intent);
                   // Toast.makeText(QuizActivity.this, "QUIZ ENDS", Toast.LENGTH_LONG).show();
                }
                }
               // objectAnimator.cancel();
            }, 3000);
            wasViewClicked = true;
            // MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.auswahlrunde_start);
            // mediaPlayer.start();

        }
    }
}

