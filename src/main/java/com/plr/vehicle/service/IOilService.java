package com.plr.vehicle.service;

import com.plr.vehicle.pojo.OilPojo;

import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IOilService {

    /**
     * 获取权限内油卡个数
     * @param oil
     * @param authorityIds
     * @return
     */
    int getAccessOilCount(OilPojo oil, List<String> authorityIds);
    /**
     * 获取权限内油卡
     * @param oil
     * @param authorityIds
     * @return
     */
    List<OilPojo> getAccessOil(OilPojo oil, List<String> authorityIds);

    /**
     * 删除油卡
     * @param list
     * @return
     * @throws Exception
     */
    int deleteOil(List<String> list) throws Exception;

    /**
     * 查询oilCode是否已存在
     * @param oilId
     * @param oilCode
     * @return
     */
    int existUpdateOil(String oilId, String oilCode);

    /**
     * 更新油卡信息
     * @param oil
     * @return
     */
    int updateOil(OilPojo oil);

    /**
     * 新增油卡
     * @param oil
     * @return
     */
    int insertOil(OilPojo oil);
}
