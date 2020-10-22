package com.ohm.missingpeople.utils;

public interface ISharedPreferenceHelper {

    void updateLoginCreds(String userName,String userPass);
    boolean checkLoginCreds();
    void deleteLoginCreds();
    void saveUserData(String fName,String lName,String contactNumber,String token);

    String getFName();
    String getLName();
    String getContactNum();
    String getToken();
    boolean checkRememberMe();
    void setRememberMe(boolean rememberMe);




}
