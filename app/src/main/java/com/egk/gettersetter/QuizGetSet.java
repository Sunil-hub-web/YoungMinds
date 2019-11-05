package com.egk.gettersetter;

public class QuizGetSet {

    public String Number;
    public String Name;
    public String Mark;

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


    public QuizGetSet(String number, String name, String mark) {
        Number = number;
        Name = name;
        Mark = mark;
    }



}
