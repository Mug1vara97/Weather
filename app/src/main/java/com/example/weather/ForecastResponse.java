package com.example.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {

    @SerializedName("list")
    private List<Forecast> forecastList;

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
