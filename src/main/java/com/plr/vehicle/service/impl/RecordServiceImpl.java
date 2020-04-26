package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IOilDao;
import com.plr.vehicle.dao.IRecordDao;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.pojo.OilPojo;
import com.plr.vehicle.service.IRecordService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 11:01
 */
@Service("recordService")
public class RecordServiceImpl implements IRecordService {
    @Resource
    private IRecordDao recordDao;
    @Resource
    private IOilDao oilDao;
    @Resource
    private IdWorkerUtil idWorkerUtil;

    @Override
    public ResultModel insertRentRecord(long driverId, long vehicleId, String beginTime, String endTime, double totalCost,long optUser) {
        return recordDao.insertRentRecord(idWorkerUtil.nextId(), driverId, vehicleId, beginTime, endTime, totalCost, optUser) > 0 ? ResultModel.getInstance(true, null) : ResultModel.getInstance(false, "数据添加失败");
    }

    @Override
    public ResultModel insertOilRecord(String oilCode, String vehicleCode, double oilCost, long optUser) {
        OilPojo oilPojo = new OilPojo();
        oilPojo.setOilCode(oilCode);
        List<OilPojo> oils = oilDao.getAccessOil(oilPojo, null);
        OilPojo oil = oils.get(0);
        oil.setOilBalance(oil.getOilBalance() - oilCost);
        oilDao.updateOil(oil);
        return recordDao.insertOilRecord(idWorkerUtil.nextId(), oilCode, vehicleCode, oilCost, optUser) > 0 ? ResultModel.getInstance(true, null) : ResultModel.getInstance(false, "数据添加失败");
    }

    @Override
    public ResultModel insertRepairRecord(String vehicleCode, String beginTime, String endTime, double repairCost, long optUser) {
        return recordDao.insertRepairRecord(idWorkerUtil.nextId(), vehicleCode, beginTime, endTime, repairCost, optUser) > 0 ? ResultModel.getInstance(true, null) : ResultModel.getInstance(false, "数据添加失败");
    }
}
