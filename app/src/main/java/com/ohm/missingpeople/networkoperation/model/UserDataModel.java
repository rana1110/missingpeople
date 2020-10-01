package com.ohm.missingpeople.networkoperation.model;

import com.google.gson.annotations.SerializedName;

public class UserDataModel {
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

    public UserDataModel(String nameOfUser, String number, String usermobileno, String country2charcode, String country3charcode, String countrystdcode) {
        this.nameOfUser = nameOfUser;
        this.number = number;
        this.usermobileno = usermobileno;
        this.country2charcode = country2charcode;
        this.country3charcode = country3charcode;
        this.countrystdcode = countrystdcode;
    }
}
