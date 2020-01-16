package com.plr.vehicle.model;

public class ParentMecModel {
    private long mecId;
    private String mecName;

    public long getMecId() {
        return mecId;
    }

    public void setMecId(long mecId) {
        this.mecId = mecId;
    }

    public String getMecName() {
        return mecName;
    }

    public void setMecName(String mecName) {
        this.mecName = mecName;
    }

    @Override
    public String toString() {
        return "ParentMecModel{" +
                "mecId=" + mecId +
                ", mecName='" + mecName + '\'' +
                '}';
    }
}
