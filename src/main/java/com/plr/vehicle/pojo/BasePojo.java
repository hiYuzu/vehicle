package com.plr.vehicle.pojo;

import com.plr.vehicle.util.DateUtil;

import java.sql.Timestamp;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:32
 */
public class BasePojo {
    /**
     * 查询个数
     */
    private int rowCount;
    /**
     * 开始查询位置
     */
    private int rowIndex;

    /**
     * 操作用户ID
     */
    private long optUser;

    /**
     * 操作用户名称
     */
    private String optUserName;

    /**
     * 操作时间
     */
    private Timestamp optTime = DateUtil.GetSystemDateTime(0);

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public long getOptUser() {
        return optUser;
    }

    public void setOptUser(long optUser) {
        this.optUser = optUser;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }
}
