package com.plr.vehicle.pojo;
import com.plr.vehicle.util.DefaultParam;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:33
 */
public class UserPojo extends BasePojo {

    private long userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userTel;
    private String userEmail;
    private boolean userDelete = DefaultParam.DEL_DEFAULT;
    private String userRemark;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isUserDelete() {
        return userDelete;
    }

    public void setUserDelete(boolean userDelete) {
        this.userDelete = userDelete;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    @Override
    public boolean equals(Object obj) {
        return this.userId == ((UserPojo) obj).getUserId();
    }
}
