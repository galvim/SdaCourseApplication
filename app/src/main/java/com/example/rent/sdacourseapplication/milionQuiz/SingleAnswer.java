package com.example.rent.sdacourseapplication.milionQuiz;

import java.util.List;

/**
 * Created by RENT on 2017-02-25.
 */

public class SingleAnswer {

    private boolean isCorrect;
    private String text;
    private List<SingleAnswer> answers;

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getText() {
        return text;
    }

}
