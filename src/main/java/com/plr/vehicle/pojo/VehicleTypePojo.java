package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:34
 */
public class VehicleTypePojo extends BasePojo {

    private long vtId;
    private String vtCode;
    private String vtName;
    private long vtType;

    public long getVtId() {
        return vtId;
    }

    public void setVtId(long vtId) {
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

    public long getVtType() {
        return vtType;
    }

    public void setVtType(long vtType) {
        this.vtType = vtType;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "vtId=" + vtId +
                ", vtCode='" + vtCode + '\'' +
                ", vtName='" + vtName + '\'' +
                ", vtType=" + vtType +
                '}';
    }
}
