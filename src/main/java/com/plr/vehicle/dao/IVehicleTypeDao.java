package com.plr.vehicle.dao;

import com.plr.vehicle.model.VehicleTypeModel;
import com.plr.vehicle.pojo.VehicleTypePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IVehicleTypeDao {
    List<Map<String,String>> getTypeIdAndTypeName();

    int getVehicleTypeCount(@Param("typeCode") String typeCode, @Param("typeName") String typeName);

    List<VehicleTypeModel> listVehicleType(@Param("rowIndex") int rowIndex, @Param("rowCount") int rowCount, @Param("typeCode") String typeCode, @Param("typeName") String typeName);

    List<Long> getNextVehicleType(@Param("vehicleTypeId") long vehicleTypeId);

    int deleteVehicleTypeById(@Param("vehicleTypeId") long vehicleTypeId);

    VehicleTypeModel getVehicleTypeByCode(@Param("vtCode") String vtCode);

    int insertVehicleType(@Param("pojo") VehicleTypePojo pojo);

    int updateVehicleType(@Param("pojo") VehicleTypePojo pojo);
}
