package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/2 9:01
 */
public class OilRecordModel {

    private String vehicleName;
    private String oilCode;
    private String oilCost;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getOilCode() {
        return oilCode;
    }

    public void setOilCode(String oilCode) {
        this.oilCode = oilCode;
    }

    public String getOilCost() {
        return oilCost;
    }

    public void setOilCost(String oilCost) {
        this.oilCost = oilCost;
    }
}
