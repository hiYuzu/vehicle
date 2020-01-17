package com.plr.vehicle.service;


import com.plr.vehicle.model.VehicleTypeModel;
import com.plr.vehicle.pojo.VehicleTypePojo;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IVehicleTypeService {
    /**
     * 获取所有类型ID和类型名称
     * @return
     */
    List<Map<String,String>> getTypeIdAndTypeName();

    /**
     * 获取类型数量
     * @return
     */
    int getVehicleTypeCount();

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param typeCode
     * @param typeName
     * @return
     */
    List<VehicleTypeModel> listVehicleType(int page, int limit, String typeCode, String typeName);

    /**
     * 根据id删除vehicleType
     * @param vehicleTypeId
     * @return -1删除失败，存在子类型
     */
    int deleteVehicleTypeById(long vehicleTypeId);

    /**
     * 插入车辆类型
     * @param pojo
     * @return
     */
    int insertVehicleType(VehicleTypePojo pojo);

    /**
     * 更新VehicleType
     * @param pojo
     * @return
     */
    int updateVehicleType(VehicleTypePojo pojo);

    /**
     * 根据车辆类型名模糊查新时，获取车辆类型数量
     * @param vehicleTypeName
     * @return
     */
    int getCountByTName(String vehicleTypeName);

    /**
     * 根据车辆类型编码模糊查新时，获取车辆类型数量
     * @param vehicleTypeCode
     * @return
     */
    int getCountByTCode(String vehicleTypeCode);
}
