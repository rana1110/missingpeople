package com.ohm.missingpeople.activity.authentication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

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

public class ChangePassword extends BaseActivity {
    private TextInputEditText oldPass, newPass, confirmPass;
    private boolean stringComapreFlage = false;
    ISharedPreferenceHelper iSharedPreferenceHelper;
    ApiInterface apiInterface;
    Call<ChangePasswordModel> changePasswordModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        oldPass = (TextInputEditText) findViewById(R.id.change_pass_old_password);
        newPass = (TextInputEditText) findViewById(R.id.change_pass_new_password);
        confirmPass = (TextInputEditText) findViewById(R.id.change_pass_confirm_password);
        oldPass.addTextChangedListener(passwordWatcher);
        newPass.addTextChangedListener(passwordWatcher);
        confirmPass.addTextChangedListener(passwordWatcher);

    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            /*Log.e("test1234 confirm", "" + s);
            Log.e("test1234 new", "" + newPass.getText());*/
            stringCompare();
        }
    };

    private void stringCompare() {
        if (newPass.getText().toString().isEmpty() || newPass.getText().toString().isEmpty()) {
            stringComapreFlage = false;
            confirmPass.setCompoundDrawables(null, null,
                    null, null);
            newPass.setCompoundDrawables(null, null,
                    null, null);
        } else if (newPass.getText().toString().equals(confirmPass.getText().toString()) &&
                !oldPass.getText().toString().isEmpty()) {
            Log.e("test1234", "Pass match");
            stringComapreFlage = true;
            confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_match_icon), null);
            newPass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_match_icon), null);
        } else {
            Log.e("test1234", "Pass mis match");
            newPass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_mismatch_icon), null);
            confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                    getDrawable(R.drawable.password_mismatch_icon), null);
            stringComapreFlage = false;
        }
    }

    public void changePasswordOnClick(View v) {
        if (oldPass.getText().toString().isEmpty()) {
            showError("Enter Old Password");
            oldPass.requestFocus();
        } else if (newPass.getText().toString().isEmpty()) {
            showError("Enter New Password");
            newPass.requestFocus();
        } else if (confirmPass.getText().toString().isEmpty()) {
            showError("Enter Confirm Password");
            confirmPass.requestFocus();
        } else if (!stringComapreFlage) {
            showError("New And Confirm Password is not Matching");
        } else {
            callResetPassword();
        }
    }

    private void callResetPassword() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        changePasswordModelCall = apiInterface.changePassword(iSharedPreferenceHelper.getToken(), newPass.getText().toString());
        changePasswordModelCall.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                if (response.body().getStatus().equals("true") && !response.body().getToken().isEmpty()) {
                //    iSharedPreferenceHelper.setToken(response.body().getToken());
                    //showError("Password Change Successfully");
                    showToast("Password Change Successfully");
                    iSharedPreferenceHelper.deleteLoginCreds();
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