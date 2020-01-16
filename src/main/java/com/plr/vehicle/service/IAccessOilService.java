package com.plr.vehicle.service;

import com.plr.vehicle.pojo.AccessOilPojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IAccessOilService {
    /**
     * 撤销所有油卡权限
     *
     * @param authorityId
     * @return
     * @throws Exception
     */
    void revokeOilPermission(long authorityId) throws Exception;

    /**
     * 插入油卡权限信息
     *
     * @param accessOil
     * @throws Exception
     */
    void insertAccessOil(AccessOilPojo accessOil) throws Exception;
}
