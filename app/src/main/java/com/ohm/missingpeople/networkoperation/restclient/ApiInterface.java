package com.ohm.missingpeople.networkoperation.restclient;

import com.ohm.missingpeople.networkoperation.model.AllMissingPeople;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/missingperson/getmissingppl")
    Call<AllMissingPeople> getMissingPeopleDetail();


}
