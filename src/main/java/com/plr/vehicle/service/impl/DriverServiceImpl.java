package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IDriverDao;
import com.plr.vehicle.model.DriverModel;
import com.plr.vehicle.pojo.DriverPojo;
import com.plr.vehicle.service.IDriverService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("driverService")
public class DriverServiceImpl implements IDriverService {
    @Resource
    private IDriverDao driverDao;

    @Autowired
    private IdWorkerUtil idWorkerUtil;

    @Override
    public int getDriverCount() {
        return driverDao.getDriverCount();
    }

    @Override
    public List<DriverModel> listDriver(int page, int limit, String driverName, int isUsable) {
        int rowIndex = (page - 1) * limit;
        return driverDao.listDriver(rowIndex, limit, driverName, isUsable);
    }

    @Override
    public int deleteDriverById(long driverId) {
        return driverDao.deleteDriverById(driverId);
    }

    @Override
    public int insertDriver(DriverPojo pojo) {
        pojo.setDriverId(idWorkerUtil.nextId());
        return driverDao.insertDriver(pojo);
    }

    @Override
    public int updateDriver(DriverPojo pojo) {
        return driverDao.updateDriver(pojo);
    }
}
