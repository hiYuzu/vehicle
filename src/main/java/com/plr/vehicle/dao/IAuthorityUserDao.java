package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.AuthorityUserPojo;
import org.apache.ibatis.annotations.Param;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IAuthorityUserDao {
    /**
     * 撤销所有设备权限
     *
     * @param authorityId
     * @param userId
     * @return
     * @throws Exception
     */
    void revokeUserPermission(@Param("authorityId") long authorityId, @Param("userId") long userId);

    /**
     * 插入设备权限信息
     *
     * @param authorityUser
     * @throws Exception
     */
    void insertAuthorityUser(@Param("authorityUser") AuthorityUserPojo authorityUser);

    /**
     * 验证是否已存在此权限信息
     * @param authorityUser
     * @return
     */
    int isExist(@Param("authorityUser") AuthorityUserPojo authorityUser);
}
