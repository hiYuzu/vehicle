package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IOilDao;
import com.plr.vehicle.pojo.OilPojo;
import com.plr.vehicle.service.IOilService;
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
@Service("oilService")
@Transactional(rollbackFor = Exception.class)
public class OilServiceImpl implements IOilService {
    private static final String LOG = "OilServiceImpl";
    private Logger logger = LoggerFactory.getLogger(OilServiceImpl.class);

    @Resource
    private IOilDao oilDao;

    @Override
    public int getAccessOilCount(OilPojo oil, List<String> authorityIds) {
        return oilDao.getAccessOilCount(oil, authorityIds);
    }

    @Override
    public List<OilPojo> getAccessOil(OilPojo oil, List<String> authorityIds) {
        return oilDao.getAccessOil(oil, authorityIds);
    }

    @Override
    public int deleteOil(List<String> list) {
        return oilDao.deleteOil(list);
    }

    @Override
    public int existUpdateOil(String oilId, String oilCode) {
        return oilDao.existUpdateOil(oilId, oilCode);
    }

    @Override
    public int updateOil(OilPojo oil) {
        return oilDao.updateOil(oil);
    }

    @Override
    public int insertOil(OilPojo oil) {
        return oilDao.insertOil(oil);
    }

}
