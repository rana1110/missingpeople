package com.ohm.missingpeople.activity;

import android.os.Bundle;

import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.authentication.LoginActivity;
import com.ohm.missingpeople.networkoperation.model.GeneralModel;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.model.TokenCheckerModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.ISharedPreferenceHelper;
import com.ohm.missingpeople.utils.SharedPreferenceHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    ISharedPreferenceHelper iSharedPreferenceHelper;
    ApiInterface apiInterface;
    Call<LoginModel> loginModelCall;
    Call<GeneralModel> forgotPasswordCall;
    Call<TokenCheckerModel> tokenCheckerModelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //showDialog();

        iSharedPreferenceHelper = new SharedPreferenceHelper(this);
        if (iSharedPreferenceHelper.checkRememberMe()) {
            useTokenValidationService(iSharedPreferenceHelper.getToken());
            //makeScreenHolds(3);
        } else
            openNewScreen(new LoginActivity());

    }

    private void useTokenValidationService(final String token) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        tokenCheckerModelCall = apiInterface.tokenChecker(token);
        tokenCheckerModelCall.enqueue(new Callback<TokenCheckerModel>() {
            @Override
            public void onResponse(Call<TokenCheckerModel> call, Response<TokenCheckerModel> response) {
                if (response.body().getStatus().equals("true") &&
                        !response.body().getToken().equals(token)) {

                    openNewScreen(new HomeScreenActivity());
                }
            }

            @Override
            public void onFailure(Call<TokenCheckerModel> call, Throwable t) {


            }
        });
    }


}
