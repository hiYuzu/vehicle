package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:38
 */
public class OilPojo extends BasePojo {

    private long oilId;
    private String oilCode;
    private String oilManufacturer;
    private Double oilBalance;

    public long getOilId() {
        return oilId;
    }

    public void setOilId(long oilId) {
        this.oilId = oilId;
    }

    public String getOilCode() {
        return oilCode;
    }

    public void setOilCode(String oilCode) {
        this.oilCode = oilCode;
    }

    public String getOilManufacturer() {
        return oilManufacturer;
    }

    public void setOilManufacturer(String oilManufacturer) {
        this.oilManufacturer = oilManufacturer;
    }

    public Double getOilBalance() {
        return oilBalance;
    }

    public void setOilBalance(Double oilBalance) {
        this.oilBalance = oilBalance;
    }

    @Override
    public String toString() {
        return "OilPojo{" +
                "oilId=" + oilId +
                ", oilCode='" + oilCode + '\'' +
                ", oilManufacturer='" + oilManufacturer + '\'' +
                ", oilBalance='" + oilBalance + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this.oilId == ((OilPojo) obj).getOilId();
    }
}
