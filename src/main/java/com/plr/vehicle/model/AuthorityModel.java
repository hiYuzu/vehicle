package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:24
 */
public class AuthorityModel extends BaseModel {
    private String authorityId;
    private String authorityCode;
    private String authorityName;
    private String authorityRemark;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityRemark() {
        return authorityRemark;
    }

    public void setAuthorityRemark(String authorityRemark) {
        this.authorityRemark = authorityRemark;
    }
}
