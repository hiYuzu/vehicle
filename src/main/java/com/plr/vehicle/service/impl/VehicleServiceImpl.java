package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IVehicleDao;
import com.plr.vehicle.model.OilRecordModel;
import com.plr.vehicle.model.RentRecordModel;
import com.plr.vehicle.model.RepairRecordModel;
import com.plr.vehicle.pojo.VehiclePojo;
import com.plr.vehicle.service.IVehicleService;
import com.plr.vehicle.util.DateUtil;
import com.plr.vehicle.util.DefaultParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("vehicleService")
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl implements IVehicleService {

    @Resource
    private IVehicleDao vehicleDao;

    @Override
    public int getAccessVehicleCount(VehiclePojo vehicle, List<String> authorityIds) {
        return vehicleDao.getAccessVehicleCount(vehicle, authorityIds);
    }

    @Override
    public List<VehiclePojo> getAccessVehicle(VehiclePojo vehicle, List<String> authorityIds) {
        return vehicleDao.getAccessVehicle(vehicle, authorityIds);
    }

    @Override
    public int deleteVehicle(List<String> list) {
        return vehicleDao.deleteVehicle(list);
    }

    @Override
    public int existUpdateVehicle(long vehicleId, String vehicleCode) {
        return vehicleDao.existUpdateVehicle(vehicleId, vehicleCode);
    }

    @Override
    public int updateVehicle(VehiclePojo vehicle) {
        return vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public int insertVehicle(VehiclePojo vehicle) {
        return vehicleDao.insertVehicle(vehicle);
    }

    @Override
    public List<RepairRecordModel> getRepairRecord(String timeRange, String vehicleCode) {
        String beginT = null, endT = null;
        if (Integer.parseInt(timeRange) != 0) {
            beginT = DateUtil.TimestampToString(DateUtil.GetSystemDateTime(1000 * 60 * 60 * 24 * Integer.parseInt(timeRange)), DateUtil.DATA_TIME_SECOND);
            endT = DateUtil.GetSystemDateTime();
        }
        List<RepairRecordModel> repairRecordModels = vehicleDao.getRepairRecord(beginT, endT, vehicleCode);
        return repairRecordModels.size() > 0 ? repairRecordModels : new ArrayList<>();
    }

    @Override
    public List<OilRecordModel> getOilRecord(String timeRange, String vehicleCode) {
        String beginT = null, endT = null;
        if (Integer.parseInt(timeRange) != 0) {
            beginT = DateUtil.TimestampToString(DateUtil.GetSystemDateTime(1000 * 60 * 60 * 24 * Integer.parseInt(timeRange)), DateUtil.DATA_TIME_SECOND);
            endT = DateUtil.GetSystemDateTime();
        }
        List<OilRecordModel> oilRecordModels = vehicleDao.getOilRecord(beginT, endT, vehicleCode);
        return oilRecordModels.size() > 0 ? oilRecordModels : new ArrayList<>();
    }

    @Override
    public List<RentRecordModel> getRentRecord(String timeRange, String vehicleCode) {
        String beginT = null, endT = null;
        if (Integer.parseInt(timeRange) != 0) {
            beginT = DateUtil.TimestampToString(DateUtil.GetSystemDateTime(1000 * 60 * 60 * 24 * Integer.parseInt(timeRange)), DateUtil.DATA_TIME_SECOND);
            endT = DateUtil.GetSystemDateTime();
        }
        List<RentRecordModel> rentRecordModels = vehicleDao.getRentRecord(beginT, endT, vehicleCode);
        return rentRecordModels.size() > 0 ? rentRecordModels : new ArrayList<>();
    }

}
