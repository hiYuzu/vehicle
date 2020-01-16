package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:28
 */
public class AccessVehiclePojo extends BasePojo {
    private long accessVehicleId;
    private AuthorityPojo authority;
    private VehiclePojo vehicle;

    public long getAccessVehicleId() {
        return accessVehicleId;
    }

    public void setAccessVehicleId(long accessVehicleId) {
        this.accessVehicleId = accessVehicleId;
    }

    public AuthorityPojo getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityPojo authority) {
        this.authority = authority;
    }

    public VehiclePojo getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehiclePojo vehicle) {
        this.vehicle = vehicle;
    }
}
