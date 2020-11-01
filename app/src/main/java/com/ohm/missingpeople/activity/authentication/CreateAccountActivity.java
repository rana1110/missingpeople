package com.ohm.missingpeople.activity.authentication;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.model.RegistrationModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends BaseActivity {
    TextInputEditText fName, lName, email, password, mobile;
    boolean validField = false;
    ApiInterface apiInterface;
    Call<RegistrationModel> registrationModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        fName = findViewById(R.id.create_account_first_name);
        lName = findViewById(R.id.create_account_last_name);
        email = findViewById(R.id.create_account_email_address);
        password = findViewById(R.id.create_account_password);
        mobile = findViewById(R.id.create_account_mobile_number);
    }

    private void makeWebCall() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        registrationModelCall = apiInterface.registrationCall(fName.getText().toString(),
                lName.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
        registrationModelCall.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                if(response.body().getMessage().equals("Insert Successfully"))
                {
                    showErrorForDialogLayout("Success");
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {


            }
        });
    }

    public void createAccountCall(View v) {
        if (fName.getText().toString().isEmpty()) {
            showErrorForDialogLayout(Constants.FISRT_NAME_ERROR);
            fName.requestFocus();
        } else if (lName.getText().toString().isEmpty()) {
            lName.requestFocus();
            showErrorForDialogLayout(Constants.LAST_NAME_ERROR);
        } else if (email.getText().toString().isEmpty() || !isValidEmailAddress(email.getText().toString())) {
            email.requestFocus();
            showErrorForDialogLayout(Constants.EMAIL_ADDRESS_ERROR);
        } else if (password.getText().toString().isEmpty()) {
            password.requestFocus();
            showErrorForDialogLayout(Constants.PASSWORD_ERROR);
        } else if (mobile.getText().toString().isEmpty() || mobile.getText().toString().length() < 10) {
            mobile.requestFocus();
            showErrorForDialogLayout(Constants.MOBILE_ERROR);
            mobile.setText("");
        } else {
            makeWebCall();
        }


    }

    public void cancelClick(View v) {

    }

}
