package com.plr.vehicle.model;
import com.plr.vehicle.util.DefaultParam;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:24
 */
public class UserModel extends BaseModel{

    private String userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userTel;
    private boolean userDelete;
    private String userDeleteName;
    private String userEmail;
    private String userRemark;
    private String authorityName;
    private boolean checked = DefaultParam.DEL_DEFAULT;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public boolean isUserDelete() {
        return userDelete;
    }

    public void setUserDelete(boolean userDelete) {
        this.userDelete = userDelete;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getUserDeleteName() {
        return userDeleteName;
    }

    public void setUserDeleteName(String userDeleteName) {
        this.userDeleteName = userDeleteName;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userDelete=" + userDelete +
                ", userDeleteName='" + userDeleteName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRemark='" + userRemark + '\'' +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
