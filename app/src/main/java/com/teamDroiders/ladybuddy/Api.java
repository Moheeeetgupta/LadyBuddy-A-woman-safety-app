package com.teamDroiders.ladybuddy;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {



   String BASE_URL = "https://newsapi.org/v2/";
   @GET("everything")
    Call<NewsResults> getsuperHeroes(@Query( "apiKey" ) String key,
                                    //  @Query( "country" ) String country,
                                      // @Query( "category" ) String category,
                                       @Query( "pageSize") int maxResults,
                                       @Query( "q" ) String q
                                    // @Query( "sortBy" ) String sortBy
                                    // @Query( "sources" ) String sources
    );

}
