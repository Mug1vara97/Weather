package com.example.weather;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;

    @SerializedName("id")
    private int conditionCode;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getConditionCode() {
        return conditionCode;
    }
}
