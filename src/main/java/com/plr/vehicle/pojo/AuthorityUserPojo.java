package com.plr.vehicle.pojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:35
 */
public class AuthorityUserPojo extends BasePojo {
    private long authorityUserId;
    private AuthorityPojo authority;
    private UserPojo user;

    public long getAuthorityUserId() {
        return authorityUserId;
    }

    public void setAuthorityUserId(long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }

    public AuthorityPojo getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityPojo authority) {
        this.authority = authority;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthorityUser{" +
                "authorityUserId=" + authorityUserId +
                ", authorityId=" + authority +
                ", user=" + user +
                '}';
    }
}
