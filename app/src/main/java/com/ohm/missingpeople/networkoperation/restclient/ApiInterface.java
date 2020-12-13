package com.ohm.missingpeople.networkoperation.restclient;

import com.ohm.missingpeople.networkoperation.model.AllMissingPeople;
import com.ohm.missingpeople.networkoperation.model.ChangePasswordModel;
import com.ohm.missingpeople.networkoperation.model.GeneralModel;
import com.ohm.missingpeople.networkoperation.model.LoginModel;
import com.ohm.missingpeople.networkoperation.model.RegistrationModel;
import com.ohm.missingpeople.networkoperation.model.TokenCheckerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/missingperson/getmissingppl")
    Call<AllMissingPeople> getMissingPeopleDetail();

//http://api.ohmsoftwaresinc.com/api/missingperson/Login?email=rana.ankit.m@gmail.com&password=oHm@1110

    @GET("/api/missingperson/Login")
    Call<LoginModel> getLoginToApp(@Query("email") String emailAddress, @Query("password") String password);


    @GET("/api/missingperson/Changepassword")
    Call<ChangePasswordModel> changePassword(@Query("token") String token, @Query("password") String password);

    @GET("/api/missingperson/sendemailforgotpwd")
    Call<GeneralModel> forgotPasswordEmailSend(@Query("email") String emailAddress);

    @GET("/api/missingperson/Usercredentialbytoken")
    Call<TokenCheckerModel> tokenChecker(@Query("token") String token);

    @GET("/api/missingperson/Registration")
    Call<RegistrationModel> registrationCall(@Query("fname") String fName,
                                             @Query("lname") String lName,
                                             @Query("UserID") String emailAddress,
                                             @Query("password") String pass);


}
