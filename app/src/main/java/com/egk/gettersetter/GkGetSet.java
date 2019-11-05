package com.egk.gettersetter;

public class GkGetSet {

    String gk_id, category_name, gk_date, gk_title, gk_desc;

    public GkGetSet(String gk_id, String category_name, String gk_date, String gk_title, String gk_desc) {
        this.gk_id = gk_id;
        this.category_name = category_name;
        this.gk_date = gk_date;
        this.gk_title = gk_title;
        this.gk_desc = gk_desc;
    }

    public String getGk_id() {
        return gk_id;
    }

    public void setGk_id(String gk_id) {
        this.gk_id = gk_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getGk_date() {
        return gk_date;
    }

    public void setGk_date(String gk_date) {
        this.gk_date = gk_date;
    }

    public String getGk_title() {
        return gk_title;
    }

    public void setGk_title(String gk_title) {
        this.gk_title = gk_title;
    }

    public String getGk_desc() {
        return gk_desc;
    }

    public void setGk_desc(String gk_desc) {
        this.gk_desc = gk_desc;
    }
}
