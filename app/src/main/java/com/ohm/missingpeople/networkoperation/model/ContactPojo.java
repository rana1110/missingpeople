package com.ohm.missingpeople.networkoperation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactPojo {
    @SerializedName("name")
    public String nameOfUser;
    @SerializedName("number")
    public String number;
    @SerializedName("usermobileno")
    public String usermobileno;
    @SerializedName("country2charcode")
    public String country2charcode;
    @SerializedName("country3charcode")
    public String country3charcode;
    @SerializedName("countrystdcode")
    public String countrystdcode;
    @SerializedName("date")
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsermobileno() {
        return usermobileno;
    }

    public void setUsermobileno(String usermobileno) {
        this.usermobileno = usermobileno;
    }

    public String getCountry2charcode() {
        return country2charcode;
    }

    public void setCountry2charcode(String country2charcode) {
        this.country2charcode = country2charcode;
    }

    public String getCountry3charcode() {
        return country3charcode;
    }

    public void setCountry3charcode(String country3charcode) {
        this.country3charcode = country3charcode;
    }

    public String getCountrystdcode() {
        return countrystdcode;
    }

    public void setCountrystdcode(String countrystdcode) {
        this.countrystdcode = countrystdcode;
    }
}
