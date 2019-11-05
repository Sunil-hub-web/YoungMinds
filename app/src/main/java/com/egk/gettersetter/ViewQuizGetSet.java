package com.egk.gettersetter;

public class ViewQuizGetSet {

    public  String Ses_id;
    public  String Ses_name;
    public  String StartDate;
    public  String StartTime;
    public  String TimerQuest;
    public  String MarkQuest;

    public String getSes_id() {
        return Ses_id;
    }

    public void setSes_id(String ses_id) {
        Ses_id = ses_id;
    }

    public String getSes_name() {
        return Ses_name;
    }

    public void setSes_name(String ses_name) {
        Ses_name = ses_name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getTimerQuest() {
        return TimerQuest;
    }

    public void setTimerQuest(String timerQuest) {
        TimerQuest = timerQuest;
    }

    public String getMarkQuest() {
        return MarkQuest;
    }

    public void setMarkQuest(String markQuest) {
        MarkQuest = markQuest;
    }

    public ViewQuizGetSet(String ses_id, String ses_name, String startDate, String startTime, String timerQuest, String markQuest) {
        Ses_id = ses_id;
        Ses_name = ses_name;
        StartDate = startDate;
        StartTime = startTime;
        TimerQuest = timerQuest;
        MarkQuest = markQuest;
    }

}
