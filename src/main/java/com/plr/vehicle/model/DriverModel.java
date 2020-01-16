package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:25
 */
public class DriverModel extends BaseModel {
    private String driverId;
    private String driverName;
    private String driverPhone;
    private String driverDeadline;
    private String driverRemark;
    private boolean isUsable;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverDeadline() {
        return driverDeadline;
    }

    public void setDriverDeadline(String driverDeadline) {
        this.driverDeadline = driverDeadline;
    }

    public String getDriverRemark() {
        return driverRemark;
    }

    public void setDriverRemark(String driverRemark) {
        this.driverRemark = driverRemark;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    @Override
    public String toString() {
        return "DriverModel{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverPhone=" + driverPhone +
                ", driverDeadline='" + driverDeadline + '\'' +
                ", driverRemark='" + driverRemark + '\'' +
                ", isUsable='" + isUsable + '\'' +
                '}';
    }
}
