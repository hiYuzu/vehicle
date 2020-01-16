package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:32
 */
public class ManufacturerPojo extends BasePojo {

    private long mfrId;
    private String mfrCode;
    private String mfrName;
    private String mfrAddress;
    private String mfrRemark;

    public long getMfrId() {
        return mfrId;
    }

    public void setMfrId(long mfrId) {
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

    @Override
    public String toString() {
        return "ManufacturerPojo{" +
                "mfrId=" + mfrId +
                ", mfrCode='" + mfrCode + '\'' +
                ", mfrName='" + mfrName + '\'' +
                ", mfrAddress='" + mfrAddress + '\'' +
                ", mfrRemark='" + mfrRemark + '\'' +
                '}';
    }
}
