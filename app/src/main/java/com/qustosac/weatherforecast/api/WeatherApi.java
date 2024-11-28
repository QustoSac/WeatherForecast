package com.qustosac.weatherforecast.api;

import com.qustosac.weatherforecast.model.ForecastResponse;
import com.qustosac.weatherforecast.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<WeatherResponse> getCurrentWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

    @GET("forecast")
    Call<ForecastResponse> getForecast(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

}
