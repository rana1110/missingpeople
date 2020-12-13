package com.ohm.missingpeople.utils;

import android.app.Application;

public class MissingPeopleApplication extends Application {

    private static MissingPeopleApplication missingPeopleApplication;

    public static MissingPeopleApplication getInstance() {
        return missingPeopleApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        missingPeopleApplication = this;
    }
}
