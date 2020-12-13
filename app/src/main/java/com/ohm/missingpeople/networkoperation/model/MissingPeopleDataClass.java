package com.ohm.missingpeople.networkoperation.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MissingPeopleDataClass {
    @SerializedName("personid")
    @Expose
    private String personid;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fathername")
    @Expose
    private String fathername;
    @SerializedName("mothername")
    @Expose
    private String mothername;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("eyes_color")
    @Expose
    private String eyesColor;
    @SerializedName("hair_color")
    @Expose
    private String hairColor;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("lastseen")
    @Expose
    private String lastseen;
    @SerializedName("missing_from")
    @Expose
    private String missingFrom;
    @SerializedName("missing_since")
    @Expose
    private String missingSince;
    @SerializedName("identity_mark")
    @Expose
    private String identityMark;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("postedby")
    @Expose
    private String postedby;
    @SerializedName("contactdetail")
    @Expose
    private String contactdetail;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("createdbydatetime")
    @Expose
    private String createdbydatetime;
    @SerializedName("modifybydatetime")
    @Expose
    private String modifybydatetime;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEyesColor() {
        return eyesColor;
    }

    public void setEyesColor(String eyesColor) {
        this.eyesColor = eyesColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getMissingFrom() {
        return missingFrom;
    }

    public void setMissingFrom(String missingFrom) {
        this.missingFrom = missingFrom;
    }

    public String getMissingSince() {
        return missingSince;
    }

    public void setMissingSince(String missingSince) {
        this.missingSince = missingSince;
    }

    public String getIdentityMark() {
        return identityMark;
    }

    public void setIdentityMark(String identityMark) {
        this.identityMark = identityMark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getContactdetail() {
        return contactdetail;
    }

    public void setContactdetail(String contactdetail) {
        this.contactdetail = contactdetail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedbydatetime() {
        return createdbydatetime;
    }

    public void setCreatedbydatetime(String createdbydatetime) {
        this.createdbydatetime = createdbydatetime;
    }

    public String getModifybydatetime() {
        return modifybydatetime;
    }

    public void setModifybydatetime(String modifybydatetime) {
        this.modifybydatetime = modifybydatetime;
    }

}
