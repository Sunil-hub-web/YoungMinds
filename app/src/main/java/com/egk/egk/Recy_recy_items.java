package com.egk.egk;

public class Recy_recy_items {

    String GkId;
    String categroyName;
    String category_icon;

    public Recy_recy_items(String gkId, String categroyName, String category_icon) {
        GkId = gkId;
        this.categroyName = categroyName;
        this.category_icon = category_icon;
    }


    public String getGkId() {
        return GkId;
    }

    public void setGkId(String gkId) {
        GkId = gkId;
    }

    public String getCategroyName() {
        return categroyName;
    }

    public void setCategroyName(String categroyName) {
        this.categroyName = categroyName;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }
}
