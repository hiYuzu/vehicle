package com.plr.vehicle.model;

public class MfrIdAndMfrNameModel {
    private String mfrId;
    private String mfrName;

    public String getMfrId() {
        return mfrId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    public String getMfrName() {
        return mfrName;
    }

    public void setMfrName(String mfrName) {
        this.mfrName = mfrName;
    }

    @Override
    public String toString() {
        return "MfrIdAndMfrNameModel{" +
                "mfrId='" + mfrId + '\'' +
                ", mfrName='" + mfrName + '\'' +
                '}';
    }
}
