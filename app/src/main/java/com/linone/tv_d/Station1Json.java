package com.linone.tv_d;

import com.google.gson.annotations.SerializedName;

public class Station1Json {

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("entry_id")
    private String entry_id;

    @SerializedName("field1")
    private String VminMax;

    @SerializedName("field2")
    private String XminAvg;

    @SerializedName("field3")
    private String Temp;

    @SerializedName("field4")
    private String Humi;

    @SerializedName("field7")
    private String Warning;

    public Station1Json(String created_at, String entry_id, String vminMax, String xminAvg, String temp, String humi, String warning) {
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

    public String getVminMax() {
        return VminMax;
    }

    public void setVminMax(String vminMax) {
        VminMax = vminMax;
    }

    public String getXminAvg() {
        return XminAvg;
    }

    public void setXminAvg(String xminAvg) {
        XminAvg = xminAvg;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getHumi() {
        return Humi;
    }

    public void setHumi(String humi) {
        Humi = humi;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        Warning = warning;
    }
}
