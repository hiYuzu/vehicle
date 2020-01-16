package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
@Repository
public interface IUserDao {

    /**
     * 查询用户个数
     * @param userPojo
     * @return
     */
    int getUserCount(@Param("userPojo") UserPojo userPojo);

    /**
     * 查询用户数据
     * @param userPojo
     * @return
     */
    List<UserPojo> getUser(@Param("userPojo") UserPojo userPojo);

    /**
     * 通过权限id获取user信息
     * @param user
     * @param authorityId
     * @return
     */
    List<UserPojo> getAccessUser(@Param("user") UserPojo user, @Param("authorityId") long authorityId);

    /**
     * 新增用户数据
     * @param userPojo
     * @return
     */
    int insertUser(@Param("userPojo") UserPojo userPojo);

    /**
     * 更新用户数据
     * @param userPojo
     * @return
     */
    int updateUser(@Param("userPojo") UserPojo userPojo);

    /**
     * 更新用户密码
     * @param userId
     * @param userPassword
     * @return
     */
    int updateUserPassword(@Param("userId") String userId, @Param("userPassword") String userPassword);

    /**
     * 删除用户数据
     * @param idList
     * @return
     */
    int deleteUsers(@Param("idList") List<String> idList);

    /**
     * 存在此用户（更新时）
     * @param userId
     * @param userCode
     * @return
     */
    int existUpdateUser(@Param("userId") String userId, @Param("userCode") String userCode);

    /**
     * 通过userId获取userCode
     * @param userId
     * @return
     */
    String getUserCodeById(@Param("userId") String userId);

    /**
     * 通过userId查询userName
     * @param userId
     * @return
     */
    String getUserNameById(@Param("userId") long userId);

}
