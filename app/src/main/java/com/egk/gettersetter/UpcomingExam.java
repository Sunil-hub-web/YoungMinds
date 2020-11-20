package com.egk.gettersetter;

public class UpcomingExam {
    public UpcomingExam(String date, String examTitle) {
        this.date = date;
        ExamTitle = examTitle;
    }

    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExamTitle() {
        return ExamTitle;
    }

    public void setExamTitle(String examTitle) {
        ExamTitle = examTitle;
    }

    String ExamTitle;

}
