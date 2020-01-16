package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAccessOilDao;
import com.plr.vehicle.pojo.AccessOilPojo;
import com.plr.vehicle.service.IAccessOilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("accessOilService")
@Transactional(rollbackFor = Exception.class)
public class AccessOilServiceImpl implements IAccessOilService {
    @Resource
    private IAccessOilDao accessOilDao;

    @Override
    public void revokeOilPermission(long authorityId) {
        accessOilDao.revokeOilPermission(authorityId);
    }

    @Override
    public void insertAccessOil(AccessOilPojo accessOil) {
        accessOilDao.insertAccessOil(accessOil);
    }
}
