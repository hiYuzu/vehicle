package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/2 9:03
 */
public class RepairRecordModel {

    private String vehicleName;
    private String beginTime;
    private String repairCost;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(String repairCost) {
        this.repairCost = repairCost;
    }
}
