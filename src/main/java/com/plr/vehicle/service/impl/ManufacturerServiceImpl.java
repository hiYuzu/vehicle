package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IManufacturerDao;
import com.plr.vehicle.model.ManufacturerModel;
import com.plr.vehicle.pojo.ManufacturerPojo;
import com.plr.vehicle.service.IManufacturerService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service
@Transactional
public class ManufacturerServiceImpl implements IManufacturerService {

    @Resource
    private IManufacturerDao mfrDao;

    @Autowired
    private IdWorkerUtil idWorkerUtil;

    @Override
    public int getManufacturerCount() {
        return mfrDao.getManufacturerCount(null, null);
    }

    @Override
    public List<ManufacturerModel> listManufacturer(int page, int limit) {
        int rowIndex = (page - 1) * limit;
        int rowCount = limit;
        List<ManufacturerPojo> datas = mfrDao.listManufacturer(rowIndex, rowCount, null, null);
        List<ManufacturerModel> list = convertToModel(datas);
        return list;
    }

    @Override
    public List<ManufacturerModel> listManufacturerByMfrName(String mfrName, int page, int limit) {
        List<ManufacturerPojo> datas = mfrDao.listManufacturer((page - 1) * limit, limit, null, mfrName);
        return convertToModel(datas);
    }

    @Override
    public List<ManufacturerModel> listManufacturerByMfrCode(String mfrCode, int page, int limit) {
        List<ManufacturerPojo> datas = mfrDao.listManufacturer((page - 1) * limit, limit, mfrCode, null);
        return convertToModel(datas);
    }

    @Override
    public List<Map<String, String>> listMfrIdAndMfrName() {
        return mfrDao.listMfrIdAndMfrName();
    }

    private List<ManufacturerModel> convertToModel(List<ManufacturerPojo> datas) {
        List<ManufacturerModel> result = new ArrayList<>();
        for (ManufacturerPojo data : datas) {
            result.add(ManufacturerModel.valueOf(data));
        }
        return result;
    }

    @Override
    public int deleteManufacturer(List<Long> listMfrId) {
        int result = 0;
        for (long mfrId : listMfrId) {
            result += mfrDao.deleteManufacturerById(mfrId);
        }
        return result;
    }

    @Override
    public int deleteManufacturerById(Long mfrId) {
        return mfrDao.deleteManufacturerById(mfrId);
    }

    @Override
    public int insertManufacturer(ManufacturerPojo manufacturerPojo) {
        manufacturerPojo.setMfrId(idWorkerUtil.nextId());
        if (isExistMfrCode(manufacturerPojo.getMfrCode())) {
            return -1;
        }
        System.out.println(manufacturerPojo);
        return mfrDao.insertManufacturer(manufacturerPojo);
    }

    @Override
    public int updateManufacturer(ManufacturerPojo manufacturerPojo) {
        ManufacturerPojo pojo = mfrDao.getManufacturerByCode(manufacturerPojo.getMfrCode());
        if (pojo == null || pojo.getMfrId() == manufacturerPojo.getMfrId()) {
            return mfrDao.updateManufacturer(manufacturerPojo);
        }
        return -1;
    }

    @Override
    public int getMfrCountByMfrName(String mfrName) {
        return mfrDao.getManufacturerCount(null, mfrName);
    }

    @Override
    public int getMfrCountByMfrCode(String mfrCode) {
        return mfrDao.getManufacturerCount(mfrCode, null);
    }

    private boolean isExistMfrCode(String mfrCode) {
        ManufacturerPojo pojo = mfrDao.getManufacturerByCode(mfrCode);
        if (pojo != null) {
            return true;
        }
        return false;
    }
}
