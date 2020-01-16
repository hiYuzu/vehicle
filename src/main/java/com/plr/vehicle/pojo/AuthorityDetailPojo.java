package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:39
 */
public class AuthorityDetailPojo extends BasePojo {
    private long authorityDetailId;
    private AuthorityPojo authority;
    private long controlId;
    private int dealType;
    private String checkStatus;

    public long getAuthorityDetailId() {
        return authorityDetailId;
    }

    public void setAuthorityDetailId(long authorityDetailId) {
        this.authorityDetailId = authorityDetailId;
    }

    public AuthorityPojo getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityPojo authority) {
        this.authority = authority;
    }

    public long getControlId() {
        return controlId;
    }

    public void setControlId(long controlId) {
        this.controlId = controlId;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "AuthorityDetail{" +
                "authorityDetailId=" + authorityDetailId +
                ", authority=" + authority +
                ", controlId=" + controlId +
                ", dealType=" + dealType +
                ", checkStatus='" + checkStatus + '\'' +
                '}';
    }
}
