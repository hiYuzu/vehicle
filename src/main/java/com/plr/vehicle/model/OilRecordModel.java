package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/2 9:01
 */
public class OilRecordModel {

    private String vehicleName;
    private String oilManufacturer;
    private String oilCost;
    private String optTime;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getOilManufacturer() {
        return oilManufacturer;
    }

    public void setOilManufacturer(String oilManufacturer) {
        this.oilManufacturer = oilManufacturer;
    }

    public String getOilCost() {
        return oilCost;
    }

    public void setOilCost(String oilCost) {
        this.oilCost = oilCost;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
}
