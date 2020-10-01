package com.ohm.missingpeople.networkoperation.model;

public class CountryModel {

    String text;
    Integer imageId;
    public CountryModel(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}
