package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apiKey);

    @GET("forecast")
    Call<ForecastResponse> getWeatherForecast(@Query("q") String city, @Query("appid") String apiKey);

}