package com.egk.gettersetter;

public class Topup_geterseter {

    String PackId;

    public String getPackId() {
        return PackId;
    }

    public void setPackId(String packId) {
        PackId = packId;
    }

    public String getPackname() {
        return Packname;
    }

    public void setPackname(String packname) {
        Packname = packname;
    }

    public String getPackDays() {
        return PackDays;
    }

    public void setPackDays(String packDays) {
        PackDays = packDays;
    }

    public String getPackCost() {
        return PackCost;
    }

    public void setPackCost(String packCost) {
        PackCost = packCost;
    }

    String Packname;
    String PackDays;
    String PackCost;


    public Topup_geterseter(String packId, String packname, String packDays, String packCost) {
        PackId = packId;
        Packname = packname;
        PackDays = packDays;
        PackCost = packCost;
    }





}
