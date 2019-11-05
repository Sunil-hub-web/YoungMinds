package com.egk.egk;

public class Offer_recy_list {
    String Offerid;

    public Offer_recy_list(String offerid, String offerDDesc) {
        Offerid = offerid;
        this.offerDDesc = offerDDesc;
    }

    String offerDDesc;

    public String getOfferid() {
        return Offerid;
    }

    public void setOfferid(String offerid) {
        Offerid = offerid;
    }

    public String getOfferDDesc() {
        return offerDDesc;
    }

    public void setOfferDDesc(String offerDDesc) {
        this.offerDDesc = offerDDesc;
    }
}
