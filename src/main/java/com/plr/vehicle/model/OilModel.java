package com.plr.vehicle.model;

import com.plr.vehicle.util.DefaultParam;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:33
 */
public class OilModel extends BaseModel {
    private String oilId;
    private String oilCode;
    private String oilManufacturer;
    private String oilBalance;
    private boolean checked = DefaultParam.DEL_DEFAULT;

    public String getOilId() {
        return oilId;
    }

    public void setOilId(String oilId) {
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

    public String getOilBalance() {
        return oilBalance;
    }

    public void setOilBalance(String oilBalance) {
        this.oilBalance = oilBalance;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}