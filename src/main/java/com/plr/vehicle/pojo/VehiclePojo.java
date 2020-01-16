package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:54
 */
public class VehiclePojo extends BasePojo {
    private long vehicleId;
    private String vehicleCode;
    private String vehicleName;
    private String vehicleBrand;
    private VehicleTypePojo vehicleType;
    private ManufacturerPojo manufacturer;
    private boolean isUsable;
    private int vehiclePrice;

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
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

    public VehicleTypePojo getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypePojo vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ManufacturerPojo getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerPojo manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    public int getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(int vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @Override
    public String toString() {
        return "VehiclePojo{" +
                "vehicleId=" + vehicleId +
                ", vehicleCode='" + vehicleCode + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleType=" + vehicleType +
                ", manufacturer=" + manufacturer +
                ", isUsable='" + isUsable + '\'' +
                ", vehiclePrice='" + vehiclePrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this.vehicleId == ((VehiclePojo) obj).getVehicleId();
    }
}
