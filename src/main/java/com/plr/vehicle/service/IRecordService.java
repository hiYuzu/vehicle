package com.plr.vehicle.service;

import com.plr.vehicle.model.ResultModel;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 10:56
 */
public interface IRecordService {
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
    ResultModel insertRentRecord(long driverId, long vehicleId, String beginTime, String endTime, double totalCost, long optUser);

    /**
     * 加油记录
     * @param oilCode
     * @param vehicleCode
     * @param oilCost
     * @param optUser
     * @return
     */
    ResultModel insertOilRecord(String oilCode, String vehicleCode, double oilCost, long optUser);

    /**
     * 维修记录
     * @param vehicleCode
     * @param beginTime
     * @param endTime
     * @param repairCost
     * @param optUser
     * @return
     */
    ResultModel insertRepairRecord(String vehicleCode, String beginTime, String endTime, double repairCost, long optUser);
}
