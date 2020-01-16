package com.plr.vehicle.service;

import com.plr.vehicle.pojo.AuthorityUserPojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IAuthorityUserService {
    /**
     * 撤销所有设备权限
     *
     * @param authorityId
     * @param userId
     * @return
     * @throws Exception
     */
    void revokeUserPermission(long authorityId, long userId) throws Exception;

    /**
     * 插入设备权限信息
     *
     * @param authorityUser
     * @throws Exception
     */
    void insertAuthorityUser(AuthorityUserPojo authorityUser) throws Exception;
}
