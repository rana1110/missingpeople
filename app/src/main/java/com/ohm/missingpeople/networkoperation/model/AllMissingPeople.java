package com.ohm.missingpeople.networkoperation.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllMissingPeople {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<MissingPeopleDataClass> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MissingPeopleDataClass> getData() {
        return data;
    }

    public void setData(List<MissingPeopleDataClass> data) {
        this.data = data;
    }

}