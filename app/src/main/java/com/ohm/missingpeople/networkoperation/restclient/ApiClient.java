package com.ohm.missingpeople.networkoperation.restclient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//News Api key - 636370f44b5f434697ad16bcb9ecc62e

    //https://newsapi.org/v2/top-headlines?country=in&apiKey=636370f44b5f434697ad16bcb9ecc62e
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://api.ohmsoftwaresinc.com/";

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
