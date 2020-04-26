package com.plr.vehicle.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 12:18
 */
public interface IRecordDao {
    /**
     * 出租记录
     * @param id
     * @param driverId
     * @param vehicleId
     * @param beginTime
     * @param endTime
     * @param totalCost
     * @param optUser
     * @return
     */
    int insertRentRecord(@Param("id") long id, @Param("driverId") long driverId, @Param("vehicleId") long vehicleId, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("totalCost") double totalCost, @Param("optUser") long optUser);

    /**
     * 加油记录
     * @param id
     * @param oilCode
     * @param vehicleCode
     * @param oilCost
     * @param optUser
     * @return
     */
    int insertOilRecord(@Param("id") long id, @Param("oilCode") String oilCode, @Param("vehicleCode") String vehicleCode, @Param("oilCost") double oilCost, @Param("optUser") long optUser);

    /**
     * 维修记录
     * @param id
     * @param vehicleCode
     * @param beginTime
     * @param endTime
     * @param repairCost
     * @param optUser
     * @return
     */
    int insertRepairRecord(@Param("id") long id, @Param("vehicleCode") String vehicleCode, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("repairCost") double repairCost, @Param("optUser") long optUser);
}
