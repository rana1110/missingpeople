package com.ohm.missingpeople.activity.authentication;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.ChangePasswordModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {
    ISharedPreferenceHelper iSharedPreferenceHelper;
    TextInputEditText forgotPasswordNewPassword, forgotPasswordConfirmPassword;
    Button changePasswordButton, changePasswordCancelButton;
    private boolean stringComapreFlage = false;
    private static String TAG = "ForgotPassword";
    ApiInterface apiInterface;
    Call<ChangePasswordModel> changePasswordModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        Uri uri = this.getIntent().getData();
        if (uri != null && !uri.getQueryParameter("token").isEmpty()) {
            Log.e("test123", "url - " + uri.getQueryParameter("token"));
            setContentView(R.layout.activity_forgot_password);
            forgotPasswordNewPassword = (TextInputEditText) findViewById(R.id.activity_change_pass_new_password);
            forgotPasswordConfirmPassword = (TextInputEditText) findViewById(R.id.activity_change_pass_confirm_password);
            changePasswordButton = (Button) findViewById(R.id.activity_forgot_password_update_button);
            changePasswordCancelButton = (Button) findViewById(R.id.activity_forgot_password_cancel_button);
            forgotPasswordNewPassword.addTextChangedListener(passwordWatcher);
            forgotPasswordConfirmPassword.addTextChangedListener(passwordWatcher);

        } else {
            openNewScreen(new LoginActivity());
        }
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            stringCompare();
        }
    };

    private void stringCompare() {
        if (forgotPasswordNewPassword.getText().toString().isEmpty() || forgotPasswordConfirmPassword.getText().toString().isEmpty()) {
            stringComapreFlage = false;
            /*forgotPasswordNewPassword.setCompoundDrawables(null, null,
                    null, null);
            forgotPasswordConfirmPassword.setCompoundDrawables(null, null,
                    null, null);*/
        } else if (forgotPasswordNewPassword.getText().toString().equals(forgotPasswordConfirmPassword.getText().toString())) {
            //Log.e(TAG, "Pass match");
            showErrorForDialogLayout("New And Confirm Password is matching. Click on Change Password");
            stringComapreFlage = true;
            /*forgotPasswordNewPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.password_match_icon),
                    null, null, null);
            forgotPasswordConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_match_icon), null);*/
        } else {
            //Log.e(TAG, "Pass mis match");
            /*forgotPasswordNewPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_mismatch_icon), null);
            forgotPasswordConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_mismatch_icon), null);*/
            stringComapreFlage = false;
        }
    }

    public void changePasswordLinkOnClick(View v) {
        if (forgotPasswordNewPassword.getText().toString().isEmpty()) {
            showError("Enter Old Password");
            forgotPasswordNewPassword.requestFocus();
        } else if (forgotPasswordConfirmPassword.getText().toString().isEmpty()) {
            showError("Enter New Password");
            forgotPasswordConfirmPassword.requestFocus();
        } else if (!stringComapreFlage) {
            showError("New And Confirm Password is not Matching");
        } else {
            showError("Password Matching");
            Log.e("test123",iSharedPreferenceHelper.getToken());
            callResetPassword();
        }
    }

    private void callResetPassword() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        changePasswordModelCall = apiInterface.changePassword(iSharedPreferenceHelper.getToken(), forgotPasswordNewPassword.getText().toString());
        changePasswordModelCall.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                if (response.body().getStatus().equals("true") && !response.body().getToken().isEmpty()) {
                    iSharedPreferenceHelper.setToken(response.body().getToken());
                    Log.e("test123",""+iSharedPreferenceHelper.getToken());
                    showError("Password Change Successfully");
                    openNewScreen(new LoginActivity());
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                showError("Wrong Password");
            }
        });
    }
}
