package com.plr.vehicle.dao;

import com.plr.vehicle.model.DriverModel;
import com.plr.vehicle.pojo.DriverPojo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IDriverDao {
    int getDriverCount();

    int getDriverCountByDriverName(@Param("driverName") String driverName);

    List<DriverModel> listDriver(@Param("rowIndex") int rowIndex, @Param("rowCount") int rowCount, @Param("driverName") String driverName, @Param("isUsable") boolean isUsable);

    List<DriverModel> getNextDriverById(@Param("driverId") long driverId);

    int deleteDriverById(@Param("driverId") long driverId);

    int insertDriver(@Param("pojo") DriverPojo pojo);

    int updateDriver(@Param("pojo") DriverPojo pojo);

}
