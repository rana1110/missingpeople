package com.ohm.missingpeople.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.ohm.missingpeople.R;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

public class ForgotPasswordActivity extends BaseActivity {
    ISharedPreferenceHelper iSharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        Uri uri = this.getIntent().getData();
        if (uri != null && iSharedPreferenceHelper.getToken().equals(uri.getQueryParameter("token"))) {
            Log.e("test123", "url - " + uri.getQueryParameter("token"));
            setContentView(R.layout.activity_forgot_password);
        }
        else {
            openNewScreen(new LoginActivity());
        }

    }
}
