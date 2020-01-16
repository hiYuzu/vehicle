package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:24
 */
public class BaseModel {
    /**
     * 操作者ID
     */
    private String optUser;
    /**
     * 操作者名称
     */
    private String optUserName;
    /**
     * 操作时间
     */
    private String optTime;
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页行数
     */
    private int limit;
    /**
     * 查询个数
     */
    private int rowCount;
    /**
     * 开始查询位置
     */
    private int rowIndex;

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int rows) {
        this.limit = rows;
        this.rowCount = this.limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        setRowIndex();
    }

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

    public void setRowIndex() {
        if (this.page == 0) {
            this.page = 1;
        }
        this.rowIndex = (this.page - 1) * this.limit;
    }
}
