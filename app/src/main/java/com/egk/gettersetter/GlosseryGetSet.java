package com.egk.gettersetter;

public class GlosseryGetSet {

    String glossary_category_id,name;

    public GlosseryGetSet(String glossary_category_id, String name) {
        this.glossary_category_id = glossary_category_id;
        this.name = name;
    }

    public String getGlossary_category_id() {
        return glossary_category_id;
    }

    public void setGlossary_category_id(String glossary_category_id) {
        this.glossary_category_id = glossary_category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
