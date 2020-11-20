package com.egk.egk;

public class BasicGetSet {

   String  basicgk_id,category_id,title,descrip;

    public BasicGetSet(String basicgk_id, String category_id, String title, String descrip) {
        this.basicgk_id = basicgk_id;
        this.category_id = category_id;
        this.title = title;
        this.descrip = descrip;
    }

    public String getBasicgk_id() {
        return basicgk_id;
    }

    public void setBasicgk_id(String basicgk_id) {
        this.basicgk_id = basicgk_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
