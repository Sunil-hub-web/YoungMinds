package com.egk.gettersetter;

public class BasicGkListGetSet {
    String gk_id;
    String category_id;
    String gk_title;

    public String getGk_id() {
        return gk_id;
    }

    public void setGk_id(String gk_id) {
        this.gk_id = gk_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    String gk_desc;

    public BasicGkListGetSet(String gk_id, String category_id, String gk_title, String gk_desc) {
        this.gk_id = gk_id;
        this.category_id = category_id;
        this.gk_title = gk_title;
        this.gk_desc = gk_desc;
    }
}

