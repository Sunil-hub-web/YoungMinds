package com.egk.gettersetter;

public class Glosserytittlegetset {

    String glossary_id,title,decription;

    public Glosserytittlegetset(String glossary_id, String title, String decription) {
        this.glossary_id = glossary_id;
        this.title = title;
        this.decription = decription;
    }

    public String getGlossary_id() {
        return glossary_id;
    }

    public void setGlossary_id(String glossary_id) {
        this.glossary_id = glossary_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
