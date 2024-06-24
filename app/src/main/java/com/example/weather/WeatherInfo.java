package com.example.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {

    @SerializedName("temp")
    private double temperature;

    @SerializedName("description")
    private String description;

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
