package com.plr.vehicle.service;

import com.plr.vehicle.model.DriverModel;
import com.plr.vehicle.pojo.DriverPojo;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IDriverService {
    /**
     * 获取所有驾驶员数量
     * @return
     */
    int getDriverCount();

    /**
     * 分页查新驾驶员
     * @param page
     * @param limit
     * @param driverName
     * @return
     */
    List<DriverModel> listDriver(int page, int limit, String driverName, boolean isUsable);

    /**
     * 通过ID删除驾驶员
     * @param driverId
     * @return -1删除失败，存在上级驾驶员
     */
    int deleteDriverById(long driverId);

    /**
     * 插入驾驶员信息
     * @param pojo
     * @return
     */
    int insertDriver(DriverPojo pojo);

    /**
     * 更新驾驶员信息
     * @param pojo
     * @return
     */
    int updateDriver(DriverPojo pojo);

    /**
     * 根据驾驶员名模糊查新时，获取驾驶员数量
     * @param driverName
     * @return
     */
    int getDriverCountByDriverName(String driverName);
}
