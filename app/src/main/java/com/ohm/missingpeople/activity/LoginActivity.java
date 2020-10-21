package com.ohm.missingpeople.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.AppUtils;
import com.ohm.missingpeople.utils.Constants;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.Logger;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutFirstName;
    private TextView createAccount, changePassword, forgotPassword;
    private CheckBox rememberMeCheckBox;
    private TextInputEditText emailAddress, password;
    ISharedPreferenceHelper iSharedPreferenceHelper;
    ApiInterface apiInterface;
    Call<LoginModel> loginModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        emailAddress = (TextInputEditText) findViewById(R.id.login_email_address);
        password = (TextInputEditText) findViewById(R.id.login_password);
        createAccount = (TextView) findViewById(R.id.create_account);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        changePassword = (TextView) findViewById(R.id.change_password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me_checkbox);


    }

    private void loginToApp() {
        iSharedPreferenceHelper.deleteLoginCreds();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        loginModelCall = apiInterface.getLoginToApp(emailAddress.getText().toString().trim(), password.getText().toString().trim());
        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                String tempCheck = response.body().getMessage().toLowerCase();
                if (rememberMeCheckBox.isChecked()) {
                    Log.e("test123 ","Checkbox is checked");
                    iSharedPreferenceHelper.saveUserData(response.body().getFname(),
                            response.body().getLname(),
                            response.body().getPhoneno(),
                            "test");
                }
                if (tempCheck.equals(Constants.ACTIVE)) {
                    AppUtils.OpenNewScreen(new HomeScreenActivity());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });


    }

    public void onClickHandler(View v) {
        switch (v.getId()) {
            case R.id.create_account:
                AppUtils.openURl(getApplicationContext(), Constants.CREATE_ACCOUNT);
                break;

            case R.id.change_password:
                AppUtils.openURl(getApplicationContext(), Constants.CHANGE_PASSWORD);
                break;

            case R.id.forgot_password:
                AppUtils.openURl(getApplicationContext(), Constants.FORGOT_PASSWORD);
                break;
            case R.id.login_button:
                loginToApp();
                break;

        }

    }
}