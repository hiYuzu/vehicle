package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:41
 */
public class AccessOilPojo extends BasePojo {
    private long accessOilId;
    private AuthorityPojo authority;
    private OilPojo oil;

    public long getAccessOilId() {
        return accessOilId;
    }

    public void setAccessOilId(long accessOilId) {
        this.accessOilId = accessOilId;
    }

    public AuthorityPojo getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityPojo authority) {
        this.authority = authority;
    }

    public OilPojo getOil() {
        return oil;
    }

    public void setOil(OilPojo oil) {
        this.oil = oil;
    }
}
