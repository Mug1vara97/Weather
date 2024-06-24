package com.example.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("dt")
    private long timestamp;

    @SerializedName("main")
    private WeatherInfo weatherInfo;

    @SerializedName("weather")
    private List<Weather> weather;

    public long getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return weatherInfo.getTemperature();
    }

    public String getWeatherDescription() {
        StringBuilder weatherDescription = new StringBuilder();
        for (Weather weather : weather) {
            weatherDescription.append(weather.getDescription()).append(" ");
            weatherDescription.append(weather.getConditionCode()).append(" ");
        }
        return weatherDescription.toString();
    }


}