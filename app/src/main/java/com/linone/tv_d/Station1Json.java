package com.linone.tv_d;

import com.google.gson.annotations.SerializedName;

public class Station1Json {

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("entry_id")
    private String entry_id;

    @SerializedName("field1")
    private double VminMax;

    @SerializedName("field2")
    private double XminAvg;

    @SerializedName("field3")
    private double Temp;

    @SerializedName("field4")
    private double Humi;

    @SerializedName("field7")
    private String Warning;

    public Station1Json(String created_at, String entry_id, double vminMax, double xminAvg, double temp, double humi, String warning) {
        this.created_at = created_at;
        this.entry_id = entry_id;
        VminMax = vminMax;
        XminAvg = xminAvg;
        Temp = temp;
        Humi = humi;
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

    public double getVminMax() {
        return VminMax;
    }

    public void setVminMax(double vminMax) {
        VminMax = vminMax;
    }

    public double getXminAvg() {
        return XminAvg;
    }

    public void setXminAvg(double xminAvg) {
        XminAvg = xminAvg;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public double getHumi() {
        return Humi;
    }

    public void setHumi(double humi) {
        Humi = humi;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        Warning = warning;
    }
}
