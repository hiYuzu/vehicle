package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:31
 */
public class OrgIdAndOrgNameModel {
    private String orgId;
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "ParentOrgModel{" +
                "orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
