package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IVehicleDao;
import com.plr.vehicle.pojo.VehiclePojo;
import com.plr.vehicle.service.IVehicleService;
import com.plr.vehicle.util.DefaultParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

}
