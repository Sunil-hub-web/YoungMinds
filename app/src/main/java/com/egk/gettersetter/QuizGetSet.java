package com.egk.gettersetter;

public class QuizGetSet {

    public String Number;
    public String Name;
    public String Mark;
    public String img;


    public QuizGetSet(String number, String name, String mark, String img) {
        Number = number;
        Name = name;
        Mark = mark;
        this.img = img;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
