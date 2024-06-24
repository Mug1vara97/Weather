package com.example.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("main")
    private WeatherInfo weatherInfo;

    @SerializedName("weather")
    private List<Weather> weather;

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
