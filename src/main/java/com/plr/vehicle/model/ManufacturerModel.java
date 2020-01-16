package com.plr.vehicle.model;
import com.plr.vehicle.pojo.ManufacturerPojo;
import com.plr.vehicle.util.DateUtil;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:26
 */
public class ManufacturerModel extends BaseModel {

    private String mfrId;
    private String mfrCode;
    private String mfrName;
    private String mfrAddress;
    private String mfrRemark;

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

    public static ManufacturerModel valueOf(ManufacturerPojo manufacturerPojo){
        ManufacturerModel result = new ManufacturerModel();
        result.setMfrId(String.valueOf(manufacturerPojo.getMfrId()));
        result.setMfrName(manufacturerPojo.getMfrName());
        result.setMfrCode(manufacturerPojo.getMfrCode());
        result.setMfrAddress(manufacturerPojo.getMfrAddress());
        result.setMfrRemark(manufacturerPojo.getMfrRemark());
        result.setOptUserName(manufacturerPojo.getOptUserName());
        result.setOptTime(DateUtil.TimestampToString(manufacturerPojo.getOptTime(),DateUtil.DATA_TIME_SECOND));
        return result;
    }

    @Override
    public String toString() {
        return "ManufacturerModel{" +
                "mfrId=" + mfrId +
                ", mfrCode='" + mfrCode + '\'' +
                ", mfrName='" + mfrName + '\'' +
                ", mfrAddress='" + mfrAddress + '\'' +
                ", mfrRemark='" + mfrRemark + '\'' +
                '}';
    }
}
