package com.egk.gettersetter;

public class MontlyGetSet {
    String monthly_gk_id,monthly_gk_date,description;

    public MontlyGetSet(String monthly_gk_id, String monthly_gk_date, String description) {
        this.monthly_gk_id = monthly_gk_id;
        this.monthly_gk_date = monthly_gk_date;
        this.description = description;
    }

    public String getMonthly_gk_id() {
        return monthly_gk_id;
    }

    public void setMonthly_gk_id(String monthly_gk_id) {
        this.monthly_gk_id = monthly_gk_id;
    }

    public String getMonthly_gk_date() {
        return monthly_gk_date;
    }

    public void setMonthly_gk_date(String monthly_gk_date) {
        this.monthly_gk_date = monthly_gk_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
