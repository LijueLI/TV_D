package com.linone.tv_d;

import com.google.gson.annotations.SerializedName;

public class WarningValueJson {

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("entry_id")
    private String entry_id;

    @SerializedName("field1")
    private double Vminr;

    @SerializedName("field2")
    private double Xminr;

    @SerializedName("field3")
    private double riverw1;

    @SerializedName("field4")
    private double riverr1;

    @SerializedName("field5")
    private double hourrainr;

    @SerializedName("field6")
    private double dayrainr;

    @SerializedName("field7")
    private double riverw2;

    @SerializedName("field8")
    private double riverr2;

    public WarningValueJson(String created_at, String entry_id, double vminr, double xminr, double riverw1, double riverr1, double hourrainr, double dayrainr, double riverw2, double riverr2) {
        this.created_at = created_at;
        this.entry_id = entry_id;
        Vminr = vminr;
        Xminr = xminr;
        this.riverw1 = riverw1;
        this.riverr1 = riverr1;
        this.hourrainr = hourrainr;
        this.dayrainr = dayrainr;
        this.riverw2 = riverw2;
        this.riverr2 = riverr2;
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

    public double getVminr() {
        return Vminr;
    }

    public void setVminr(double vminr) {
        Vminr = vminr;
    }

    public double getXminr() {
        return Xminr;
    }

    public void setXminr(double xminr) {
        Xminr = xminr;
    }

    public double getRiverw1() {
        return riverw1;
    }

    public void setRiverw1(double riverw1) {
        this.riverw1 = riverw1;
    }

    public double getRiverr1() {
        return riverr1;
    }

    public void setRiverr1(double riverr1) {
        this.riverr1 = riverr1;
    }

    public double getHourrainr() {
        return hourrainr;
    }

    public void setHourrainr(double hourrainr) {
        this.hourrainr = hourrainr;
    }

    public double getDayrainr() {
        return dayrainr;
    }

    public void setDayrainr(double dayrainr) {
        this.dayrainr = dayrainr;
    }

    public double getRiverw2() {
        return riverw2;
    }

    public void setRiverw2(double riverw2) {
        this.riverw2 = riverw2;
    }

    public double getRiverr2() {
        return riverr2;
    }

    public void setRiverr2(double riverr2) {
        this.riverr2 = riverr2;
    }
}
