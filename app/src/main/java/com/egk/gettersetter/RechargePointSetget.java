package com.egk.gettersetter;

public class RechargePointSetget {

    String name,mobile,email, address,city ,pincode,gender;

    public RechargePointSetget(String name, String mobile, String email, String address, String city, String pincode, String gender) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
