package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAccessVehicleDao;
import com.plr.vehicle.pojo.AccessVehiclePojo;
import com.plr.vehicle.service.IAccessVehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("accessVehicleService")
@Transactional(rollbackFor = Exception.class)
public class AccessVehicleServiceImpl implements IAccessVehicleService {
    @Resource
    private IAccessVehicleDao accessVehicleDao;

    @Override

    public void revokeVehiclePermission(long authorityId) {
        accessVehicleDao.revokeVehiclePermission(authorityId);
    }

    @Override
    public void insertAccessVehicle(AccessVehiclePojo accessVehicle) {
        accessVehicleDao.insertAccessVehicle(accessVehicle);
    }


}
