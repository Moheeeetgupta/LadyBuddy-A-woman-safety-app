package com.teamDroiders.ladybuddy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;


    public  static Api getApi(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }
        return retrofit.create( Api.class );
    }
}
