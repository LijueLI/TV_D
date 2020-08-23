package com.linone.tv_d;

import com.google.gson.annotations.SerializedName;

public class Station3Json {

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("entry_id")
    private String entry_id;

    @SerializedName("field1")
    private double WaterLevel;

    @SerializedName("field2")
    private double Rainfall_1hour;

    @SerializedName("field3")
    private double Rainfall_24hour;

    @SerializedName("field6")
    private String Warning;

    public Station3Json(String created_at, String entry_id, double waterLevel, double rainfall_1hour, double rainfall_24hour, String warning) {
        this.created_at = created_at;
        this.entry_id = entry_id;
        WaterLevel = waterLevel;
        Rainfall_1hour = rainfall_1hour;
        Rainfall_24hour = rainfall_24hour;
        Warning = warning;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public double getWaterLevel() {
        return WaterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        WaterLevel = waterLevel;
    }

    public double getRainfall_1hour() {
        return Rainfall_1hour;
    }

    public void setRainfall_1hour(double rainfall_1hour) {
        Rainfall_1hour = rainfall_1hour;
    }

    public double getRainfall_24hour() {
        return Rainfall_24hour;
    }

    public void setRainfall_24hour(double rainfall_24hour) {
        Rainfall_24hour = rainfall_24hour;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        Warning = warning;
    }
}
