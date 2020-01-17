package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/16 10:25
 */
public class DriverPojo extends BasePojo {
    private long driverId;
    private String driverName;
    private long driverPhone;
    private String driverDeadline;
    private String driverRemark;
    private int isUsable;

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public long getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(long driverPhone) {
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

    public int isUsable() {
        return isUsable;
    }

    public void setUsable(int usable) {
        isUsable = usable;
    }

    @Override
    public String toString() {
        return "DriverPojo{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", driverDeadline='" + driverDeadline + '\'' +
                ", driverRemark=" + driverRemark +
                ", isUsable=" + isUsable +
                '}';
    }
}
