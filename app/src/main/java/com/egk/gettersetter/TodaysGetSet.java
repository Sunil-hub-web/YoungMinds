package com.egk.gettersetter;

import android.text.Spanned;

public class TodaysGetSet {

    String todays_id,todays_date,description;


    public TodaysGetSet(String todays_id, String todays_date, String description) {
        this.todays_id = todays_id;
        this.todays_date = todays_date;
        this.description = description;
    }

    public String getTodays_id() {
        return todays_id;
    }

    public void setTodays_id(String todays_id) {
        this.todays_id = todays_id;
    }

    public String getTodays_date() {
        return todays_date;
    }

    public void setTodays_date(String todays_date) {
        this.todays_date = todays_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
