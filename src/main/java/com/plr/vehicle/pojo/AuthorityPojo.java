package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:36
 */
public class AuthorityPojo extends BasePojo{
    private long authorityId;
    private String authorityCode;
    private String authorityName;
    private String authorityRemark;

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
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

    @Override
    public String toString() {
        return "AuthorityPojo{" +
                "authorityId=" + authorityId +
                ", authorityCode='" + authorityCode + '\'' +
                ", authorityName='" + authorityName + '\'' +
                ", authorityRemark='" + authorityRemark + '\'' +
                '}';
    }
}
