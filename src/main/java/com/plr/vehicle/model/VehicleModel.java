package com.plr.vehicle.model;

import com.plr.vehicle.util.DefaultParam;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:34
 */
public class VehicleModel extends BaseModel {
    private String vehicleId;
    private String vehicleCode;
    private String vehicleName;
    private String vehicleBrand;
    private int isUsable;
    private String isUsableString;
    private String vehiclePrice;
    /**
     * 车辆类型外键
     */
    private String vtId;
    private String vtCode;
    private String vtName;
    private String vtType;
    /**
     * 所属厂商外键
     */
    private String mfrId;
    private String mfrCode;
    private String mfrName;
    private String mfrAddress;
    private String mfrRemark;

    /**
     * checked
     */
    private boolean checked = DefaultParam.DEL_DEFAULT;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public int getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(int isUsable) {
        this.isUsable = isUsable;
    }

    public String getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(String vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getVtId() {
        return vtId;
    }

    public void setVtId(String vtId) {
        this.vtId = vtId;
    }

    public String getVtCode() {
        return vtCode;
    }

    public void setVtCode(String vtCode) {
        this.vtCode = vtCode;
    }

    public String getVtName() {
        return vtName;
    }

    public void setVtName(String vtName) {
        this.vtName = vtName;
    }

    public String getVtType() {
        return vtType;
    }

    public void setVtType(String vtType) {
        this.vtType = vtType;
    }

    public String getMfrId() {
        return mfrId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    public String getMfrCode() {
        return mfrCode;
    }

    public void setMfrCode(String mfrCode) {
        this.mfrCode = mfrCode;
    }

    public String getMfrName() {
        return mfrName;
    }

    public void setMfrName(String mfrName) {
        this.mfrName = mfrName;
    }

    public String getMfrAddress() {
        return mfrAddress;
    }

    public void setMfrAddress(String mfrAddress) {
        this.mfrAddress = mfrAddress;
    }

    public String getMfrRemark() {
        return mfrRemark;
    }

    public void setMfrRemark(String mfrRemark) {
        this.mfrRemark = mfrRemark;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIsUsableString() {
        return isUsableString;
    }

    public void setIsUsableString(String isUsableString) {
        this.isUsableString = isUsableString;
    }
}
