package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.AccessVehiclePojo;
import org.apache.ibatis.annotations.Param;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IAccessVehicleDao {
    /**
     * 撤销所有车辆权限
     * @param authorityId
     * @return
     */
    void revokeVehiclePermission(@Param("authorityId") long authorityId);

    /**
     * 插入车辆权限信息
     * @param accessVehicle
     */
    void insertAccessVehicle(@Param("accessVehicle") AccessVehiclePojo accessVehicle);
}
