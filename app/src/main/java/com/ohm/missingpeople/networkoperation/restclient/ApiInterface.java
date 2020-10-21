package com.ohm.missingpeople.networkoperation.restclient;

import com.ohm.missingpeople.networkoperation.model.AllMissingPeople;
import com.ohm.missingpeople.networkoperation.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/missingperson/getmissingppl")
    Call<AllMissingPeople> getMissingPeopleDetail();

//http://api.ohmsoftwaresinc.com/api/missingperson/Login?email=rana.ankit.m@gmail.com&password=oHm@1110

    @GET("/api/missingperson/Login")
    Call<LoginModel> getLoginToApp(@Query("email") String emailAddress, @Query("password") String password);


}
