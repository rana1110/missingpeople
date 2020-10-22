package com.ohm.missingpeople.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class SharedPreferenceHelper implements ISharedPreferenceHelper {

    private SharedPreferences sharedPreferences;
    private Context context;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SharedPreferenceHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_FILE_NAME, 0);
        editor = sharedPreferences.edit();
    }

    @Override
    public void updateLoginCreds(String userName, String userPass) {
        editor.putString(Constants.USERNAME, userName);
        editor.putString(Constants.PASSWORD, userPass);
        editor.commit();
    }

    @Override
    public boolean checkLoginCreds() {

        return (!Objects.requireNonNull(sharedPreferences.getString(Constants.USERNAME, null)).isEmpty()
                && !Objects.requireNonNull(sharedPreferences.getString(Constants.PASSWORD, null)).isEmpty());
    }

    @Override
    public void deleteLoginCreds() {
        editor.remove(Constants.FIRSTNAME);
        editor.remove(Constants.LASTNAME);
        editor.remove(Constants.CONTACTNUM);
        editor.remove(Constants.TOKEN);
        editor.commit();
    }

    @Override
    public void saveUserData(String fName, String lName, String contactNumber, String token) {
        editor.putString(Constants.FIRSTNAME, fName);
        editor.putString(Constants.LASTNAME, lName);
        editor.putString(Constants.CONTACTNUM, contactNumber);
        editor.putString(Constants.TOKEN, token);
        editor.commit();
    }

    @Override
    public String getFName() {
        return sharedPreferences.getString(Constants.FIRSTNAME, null);
    }

    @Override
    public String getLName() {
        return sharedPreferences.getString(Constants.LASTNAME, null);
    }

    @Override
    public String getContactNum() {
        return sharedPreferences.getString(Constants.CONTACTNUM, null);
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(Constants.TOKEN, null);
    }


    @Override
    public boolean checkRememberMe() {
        return sharedPreferences.getBoolean(Constants.REMEMBERME, false);
    }

    @Override
    public void setRememberMe(boolean rememberMe) {
        editor.putBoolean(Constants.REMEMBERME, rememberMe);
    }
}
