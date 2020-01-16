package com.plr.vehicle.service;

import com.plr.vehicle.pojo.UserPojo;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
public interface IUserService {

    /**
     * 查询用户个数
     * @param userPojo
     * @return
     */
    int getUserCount(UserPojo userPojo);

    /**
     * 查询用户数据
     * @param userPojo
     * @return
     */
    List<UserPojo> getUser(UserPojo userPojo);

    /**
     * 通过权限id获取user信息
     * @param user
     * @param authorityId
     * @return
     */
    List<UserPojo> getAccessUser(UserPojo user, long authorityId);

    /**
     * 添加用户
     * @param userPojo
     * @return
     * @throws Exception
     */
    int insertUser(UserPojo userPojo) throws Exception;

    /**
     * 更新用户
     * @param userPojo
     * @return
     * @throws Exception
     */
    int updateUser(UserPojo userPojo) throws Exception;

    /**
     * 修改密码
     * @param userId
     * @param userPassword
     * @return
     * @throws Exception
     */
    int updateUserPassword(String userId, String userPassword) throws Exception;

    /**
     * 删除用户
     * @param idList
     * @return
     * @throws Exception
     */
    int deleteUsers(List<String> idList) throws Exception;

    /**
     * 存在此用户（更新时）
     * @param userId
     * @param userCode
     * @return
     */
    int existUpdateUser(String userId, String userCode);

    /**
     * 通过userId获取userCode
     * @param userId
     * @return
     */
    String getUserCodeById(String userId);

    /**
     * 通过userId查询userName
     * @param userId
     * @return
     */
    String getUserNameById(long userId);

}
