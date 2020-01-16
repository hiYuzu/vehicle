package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IVehicleTypeDao;
import com.plr.vehicle.model.VehicleTypeModel;
import com.plr.vehicle.pojo.VehicleTypePojo;
import com.plr.vehicle.service.IVehicleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("vehicleTypeService")
@Transactional
public class VehicleTypeServiceImpl implements IVehicleTypeService {
    @Resource
    private IVehicleTypeDao vehicleTypeDao;

    @Override
    public List<Map<String, String>> getTypeIdAndTypeName() {
        return vehicleTypeDao.getTypeIdAndTypeName();
    }

    @Override
    public int getVehicleTypeCount() {
        return vehicleTypeDao.getVehicleTypeCount(null,null);
    }

    @Override
    public List<VehicleTypeModel> listVehicleType(int page, int limit, String typeCode, String typeName) {
        int rowIndex = (page-1)*limit;
        int rowCount = limit;
        return vehicleTypeDao.listVehicleType(rowIndex,rowCount,typeCode,typeName);
    }

    @Override
    public int deleteVehicleTypeById(long vehicleTypeId) {
        List<VehicleTypeModel> nextType = vehicleTypeDao.getNextVehicleType(vehicleTypeId);
        if(nextType == null || nextType.size() == 0) {
            return vehicleTypeDao.deleteVehicleTypeById(vehicleTypeId);
        }
        return -1;
    }

    @Override
    public int insertVehicleType(VehicleTypePojo pojo) {
        VehicleTypeModel result = vehicleTypeDao.getVehicleTypeByCode(pojo.getVtCode());
        if(result != null) {
            return -1;
        }
        return vehicleTypeDao.insertVehicleType(pojo);
    }

    @Override
    public int updateVehicleType(VehicleTypePojo pojo) {
        VehicleTypeModel result = vehicleTypeDao.getVehicleTypeByCode(pojo.getVtCode());
        if(result != null && Long.valueOf(result.getVtId()) != pojo.getVtId()) {
            return -1;
        }
        return vehicleTypeDao.updateVehicleType(pojo);
    }

    @Override
    public int getCountByTName(String vehicleTypeName) {
        return vehicleTypeDao.getVehicleTypeCount(null,vehicleTypeName);
    }

    @Override
    public int getCountByTCode(String vehicleTypeCode) {
        return vehicleTypeDao.getVehicleTypeCount(vehicleTypeCode,null);
    }
}
