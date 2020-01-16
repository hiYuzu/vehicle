package com.plr.vehicle.service;

import com.plr.vehicle.pojo.AccessVehiclePojo;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IAccessVehicleService {

    /**
     * 撤销所有车辆权限
     *
     * @param authorityId
     * @return
     * @throws Exception
     */
    void revokeVehiclePermission(long authorityId) throws Exception;

    /**
     * 插入车辆权限信息
     *
     * @param accessVehicle
     * @throws Exception
     */
    void insertAccessVehicle(AccessVehiclePojo accessVehicle) throws Exception;
}
