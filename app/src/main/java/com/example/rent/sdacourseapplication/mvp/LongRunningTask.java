package com.example.rent.sdacourseapplication.mvp;

public class LongRunningTask {

    public static String execute() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "UDALO SIE !!";
    }
}
