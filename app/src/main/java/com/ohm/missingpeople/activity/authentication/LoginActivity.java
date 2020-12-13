package com.ohm.missingpeople.activity.authentication;

import android.app.AlertDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.HomeScreenActivity;
import com.ohm.missingpeople.networkoperation.model.GeneralModel;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.model.TokenCheckerModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.networkoperation.restclient.NetworkOperationConstants;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private TextInputLayout textInputLayoutUserName, textInputLayoutPassword;
    private TextView createAccount, forgotPassword, forgotPasswordDialog;
    private CheckBox rememberMeCheckBox;
    private TextInputEditText emailAddress, password;
    private Button forgotPasswordBtn;
    ISharedPreferenceHelper iSharedPreferenceHelper;
    ApiInterface apiInterface;
    Call<LoginModel> loginModelCall;
    Call<GeneralModel> forgotPasswordCall;
    Call<TokenCheckerModel> tokenCheckerModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        emailAddress = (TextInputEditText) findViewById(R.id.login_email_address);
        password = (TextInputEditText) findViewById(R.id.login_password);
        createAccount = (TextView) findViewById(R.id.create_account);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
//        changePassword = (TextView) findViewById(R.id.change_password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.til_login_email_address);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.til_login_password);


    }


    private void loginToApp() {
        showDialog();
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
        } else {
            emailAddress.setText("");
            password.setText("");
            showError("Wrong Username And Password");
            emailAddress.requestFocus();

        }
        hideDialog();
    }

    public void onClickHandler(View v) {
        switch (v.getId()) {
            case R.id.create_account:
                openNewScreen(new CreateAccountActivity());
                break;
            case R.id.forgot_password:
                forgotPasswordApiCall(v);
                break;
            case R.id.login_button:
                if (emailAddress.getText().toString().isEmpty() || !isValidEmailAddress(emailAddress.getText().toString().trim()))
                    showError("Enter Valid email Address");
                else if (password.getText().toString().isEmpty())
                    showError("Enter Password");
                else
                    loginToApp();
                break;

        }

    }

    private void forgotPasswordApiCall(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.forgot_password_layout, viewGroup, false);
        forgotPasswordBtn = dialogView.findViewById(R.id.forgot_password_button);
        forgotPasswordDialog = dialogView.findViewById(R.id.forgot_password_email_address);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                forgotPasswordCall = apiInterface.forgotPasswordEmailSend(forgotPasswordDialog.getText().toString());
                forgotPasswordCall.enqueue(new Callback<GeneralModel>() {
                    @Override
                    public void onResponse(Call<GeneralModel> call, Response<GeneralModel> response) {

                        if (response.body().getMessage().equals(NetworkOperationConstants.FORGOT_PASSWORD_EMAIL_SEND_SUCCESS)) {
                            showErrorForDialogLayout("Password Link Send to Your Email");
                            alertDialog.dismiss();
                        } else {
                            showErrorForDialogLayout("Your Email is not register");
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralModel> call, Throwable t) {
                        showErrorForDialogLayout("Something went Wrong Try later");
                    }
                });


            }
        });
    }
}