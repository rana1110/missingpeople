package com.ohm.missingpeople.networkoperation.model;

import com.google.gson.annotations.SerializedName;

public class EmailAddress {

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @SerializedName("Email")
    public String Email;

    public EmailAddress(String Email) {
        this.Email = Email;
    }


}
