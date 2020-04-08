package com.plr.vehicle.dao;

import com.plr.vehicle.model.OilRecordModel;
import com.plr.vehicle.model.RepairRecordModel;
import com.plr.vehicle.pojo.VehiclePojo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IVehicleDao {
    /**
     * 查询权限车辆数量
     *
     * @param vehicle
     * @param authorityIds
     * @return
     */
    int getAccessVehicleCount(@Param("vehicle") VehiclePojo vehicle, @Param("authorityIds") List<String> authorityIds);

    /**
     * 查询权限车辆信息
     *
     * @param vehicle
     * @param authorityIds
     * @return
     */
    List<VehiclePojo> getAccessVehicle(@Param("vehicle") VehiclePojo vehicle, @Param("authorityIds") List<String> authorityIds);

    /**
     * 删除车辆
     *
     * @param list
     * @return
     */
    int deleteVehicle(@Param("list") List<String> list);

    /**
     * 查询车辆是否已存在
     *
     * @param vehicleId
     * @param vehicleCode
     * @return
     */
    int existUpdateVehicle(@Param("vehicleId") long vehicleId, @Param("vehicleCode") String vehicleCode);

    /**
     * 更新车辆信息
     *
     * @param vehicle
     * @return
     */
    int updateVehicle(@Param("vehicle") VehiclePojo vehicle);

    /**
     * 添加车辆
     *
     * @param vehicle
     * @return
     */
    int insertVehicle(@Param("vehicle") VehiclePojo vehicle);

    /**
     * 获取维修记录
     * @param beginT
     * @param endT
     * @param vehicleCode
     * @return
     */
    List<RepairRecordModel> getRepairRecord(@Param("beginT") String beginT, @Param("endT") String endT, @Param("vehicleCode") String vehicleCode);

    /**
     * 获取加油记录
     * @param beginT
     * @param endT
     * @param vehicleCode
     * @return
     */
    List<OilRecordModel> getOilRecord(@Param("beginT") String beginT, @Param("endT") String endT, @Param("vehicleCode") String vehicleCode);
}
