package com.egk.gettersetter;

public class Recy_list_items {
    String Gk;
//    String Transactions;
    int Imgone;



    //    int Imgtwo;
    public String getGk() {
        return Gk;
    }

    public void setGk(String gk) {
        Gk = gk;
    }

//    public String getTransactions() {
//        return Transactions;
//    }
//
//    public void setTransactions(String transactions) {
//        Transactions = transactions;
//    }

    public int getImgone() {
        return Imgone;
    }

    public void setImgone(int imgone) {
        Imgone = imgone;
    }

//    public int getImgtwo() {
//        return Imgtwo;
//    }
//
//    public void setImgtwo(int imgtwo) {
//        Imgtwo = imgtwo;
//    }

    public Recy_list_items(String gk, int imgone) {
        Gk = gk;
//        Transactions = transactions;
        Imgone = imgone;
//        Imgtwo = imgtwo;
    }
}
