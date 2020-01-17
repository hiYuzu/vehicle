package com.plr.vehicle.service;


import com.plr.vehicle.pojo.VehiclePojo;

import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IVehicleService {
    /**
     * 查询权限车辆数量
     * @param vehicle
     * @param authorityIds
     * @return
     */
    int getAccessVehicleCount(VehiclePojo vehicle, List<String> authorityIds);

    /**
     * 查询权限车辆信息
     * @param vehicle
     * @param authorityIds
     * @return
     */
    List<VehiclePojo> getAccessVehicle(VehiclePojo vehicle, List<String> authorityIds);

    /**
     * 删除车辆
     * @param list
     * @return
     */
    int deleteVehicle(List<String> list);

    /**
     * 查询车辆是否已存在
     * @param vehicleId
     * @param vehicleCode
     * @return
     */
    int existUpdateVehicle(long vehicleId, String vehicleCode);

    /**
     * 更新车辆信息
     * @param vehicle
     * @return
     */
    int updateVehicle(VehiclePojo vehicle);

    /**
     * 添加车辆
     * @param vehicle
     * @return
     */
    int insertVehicle(VehiclePojo vehicle);
}