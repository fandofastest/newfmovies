package com.fmovies2020.freemovies.network.apis;



import com.fmovies2020.freemovies.models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventApi {

    @GET("get_event")
    Call<List<Event>> getAllEvent(@Query("api_secret_key") String apiKey);
}