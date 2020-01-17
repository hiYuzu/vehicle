package com.plr.vehicle.dao;

import com.plr.vehicle.model.DriverModel;
import com.plr.vehicle.pojo.DriverPojo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IDriverDao {
    /**
     * 获取所有驾驶员数量
     * @return
     */
    int getDriverCount();
    /**
     * 分页查新驾驶员
     * @param rowCount
     * @param rowIndex
     * @param driverName
     * @param isUsable
     * @return
     */
    List<DriverModel> listDriver(@Param("rowIndex") int rowIndex, @Param("rowCount") int rowCount, @Param("driverName") String driverName, @Param("isUsable") int isUsable);
    /**
     * 通过ID删除驾驶员
     * @param driverId
     * @return
     */
    int deleteDriverById(@Param("driverId") long driverId);
    /**
     * 插入驾驶员信息
     * @param pojo
     * @return
     */
    int insertDriver(@Param("pojo") DriverPojo pojo);
    /**
     * 更新驾驶员信息
     * @param pojo
     * @return
     */
    int updateDriver(@Param("pojo") DriverPojo pojo);

}
