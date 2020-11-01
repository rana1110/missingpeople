package com.ohm.missingpeople.networkoperation.restclient;

import com.ohm.missingpeople.networkoperation.model.AuthenticationModel;
import com.ohm.missingpeople.networkoperation.model.GeneralModel;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.model.TokenCheckerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationNetworkOperation {
    ApiInterface apiInterface;
    Call<AuthenticationModel> authenticationModelCall;


}
