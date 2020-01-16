package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:32
 */
public class VehicleTypeModel extends BaseModel{

    private String vtId;
    private String vtCode;
    private String vtName;
    private String vtType;
    private String parentTypeName;

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

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }

    @Override
    public String toString() {
        return "VehicleTypeModel{" +
                "vtId='" + vtId + '\'' +
                ", vtCode='" + vtCode + '\'' +
                ", vtName='" + vtName + '\'' +
                ", vtType='" + vtType + '\'' +
                ", parentTypeName='" + parentTypeName + '\'' +
                '}';
    }
}