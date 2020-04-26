package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 8:49
 */
public class RentRecordModel {
    private String vehicleName;
    private String driverName;
    private String beginTime;
    private String rentCost;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getRentCost() {
        return rentCost;
    }

    public void setRentCost(String rentCost) {
        this.rentCost = rentCost;
    }
}
