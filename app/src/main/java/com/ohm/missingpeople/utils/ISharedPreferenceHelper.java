package com.ohm.missingpeople.utils;

public interface ISharedPreferenceHelper {


    void deleteLoginCreds();

    void saveUserData(String fName, String lName, String contactNumber, String token);

    String getFName();

    String getLName();

    String getContactNum();

    String getToken();

    void setToken(String token);

    boolean checkRememberMe();

    void setRememberMe(boolean rememberMe);


}
