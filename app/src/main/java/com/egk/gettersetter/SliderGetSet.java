package com.egk.gettersetter;

public class SliderGetSet  {

    String bannerid, bannername;
    int bannerimage;;

    public SliderGetSet(String bannerid, String bannername, int bannerimage) {
        this.bannerid = bannerid;
        this.bannername = bannername;
        this.bannerimage = bannerimage;
    }


    public String getBannerid() {
        return bannerid;
    }

    public void setBannerid(String bannerid) {
        this.bannerid = bannerid;
    }

    public String getBannername() {
        return bannername;
    }

    public void setBannername(String bannername) {
        this.bannername = bannername;
    }

    public int getBannerimage() {
        return bannerimage;
    }

    public void setBannerimage(int bannerimage) {
        this.bannerimage = bannerimage;
    }
}
