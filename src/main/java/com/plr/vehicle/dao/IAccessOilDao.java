package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.AccessOilPojo;
import org.apache.ibatis.annotations.Param;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IAccessOilDao {
    /**
     * 撤销所有油卡权限
     *
     * @param authorityId
     * @return
     * @throws Exception
     */
    void revokeOilPermission(@Param("authorityId") long authorityId);

    /**
     * 插入油卡权限信息
     *
     * @param accessOil
     * @throws Exception
     */
    void insertAccessOil(@Param("accessOil") AccessOilPojo accessOil);
}
