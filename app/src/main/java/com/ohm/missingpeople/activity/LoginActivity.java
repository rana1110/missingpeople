package com.ohm.missingpeople.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private TextInputLayout textInputLayoutUserName, textInputLayoutPassword;
    private TextView createAccount, changePassword, forgotPassword;
    private CheckBox rememberMeCheckBox;
    private TextInputEditText emailAddress, password;
    ISharedPreferenceHelper iSharedPreferenceHelper;
    ApiInterface apiInterface;
    Call<LoginModel> loginModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        setContentView(R.layout.activity_login);
        initView();

        /*if (iSharedPreferenceHelper.checkRememberMe())
        {

        }
        else {
            setContentView(R.layout.activity_login);
            initView();
        }*/

    }

    private void initView() {

        emailAddress = (TextInputEditText) findViewById(R.id.login_email_address);
        password = (TextInputEditText) findViewById(R.id.login_password);
        createAccount = (TextView) findViewById(R.id.create_account);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        changePassword = (TextView) findViewById(R.id.change_password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.til_login_email_address);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.til_login_password);



    }

    private void useTokenValidationService() {

    }


    private void loginToApp() {
        iSharedPreferenceHelper.deleteLoginCreds();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        loginModelCall = apiInterface.getLoginToApp(emailAddress.getText().toString().trim(), password.getText().toString().trim());
        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                saveDataToSharedPref(response);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {


            }
        });
    }

    private void saveDataToSharedPref(Response<LoginModel> response) {
        String tempCheck = response.body().getMessage().toLowerCase();
        if (rememberMeCheckBox.isChecked())
            iSharedPreferenceHelper.setRememberMe(true);
        else
            iSharedPreferenceHelper.setRememberMe(false);
        if (tempCheck.equals(Constants.ACTIVE)) {
            iSharedPreferenceHelper.saveUserData(response.body().getFname(),
                    response.body().getLname(),
                    response.body().getPhoneno(),
                    response.body().getToken());
            openNewScreen(new HomeScreenActivity());
        }
        else
        {
            emailAddress.setText("");
            password.setText("");
            showError("Wrong Username And Password");
            emailAddress.requestFocus();
        }
    }

    public void onClickHandler(View v) {
        switch (v.getId()) {
            case R.id.create_account:
                openURl(getApplicationContext(), Constants.CREATE_ACCOUNT);
                break;

            case R.id.change_password:
                openURl(getApplicationContext(), Constants.CHANGE_PASSWORD);
                break;

            case R.id.forgot_password:
                openURl(getApplicationContext(), Constants.FORGOT_PASSWORD);
                break;
            case R.id.login_button:
                if (emailAddress.getText().toString().isEmpty())
                    showError("Enter email Address");
                else if (password.getText().toString().isEmpty())
                    showError("Enter Password");
                else
                    loginToApp();
                break;

        }

    }
}