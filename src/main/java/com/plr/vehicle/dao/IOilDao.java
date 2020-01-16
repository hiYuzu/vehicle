package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.OilPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IOilDao {

    /**
     * 获取权限内油卡个数
     *
     * @param oil
     * @param authorityIds
     * @return
     */
    int getAccessOilCount(@Param("oil") OilPojo oil, @Param("authorityIds") List<String> authorityIds);

    /**
     * 获取权限内油卡
     *
     * @param oil
     * @param authorityId
     * @return
     */
    List<OilPojo> getAccessOil(@Param("oil") OilPojo oil, @Param("authorityIds") List<String> authorityId);

    /**
     * 删除油卡
     *
     * @param list
     * @return
     */
    int deleteOil(@Param("list") List<String> list);

    /**
     * 查询oilCode是否已存在
     *
     * @param oilId
     * @param oilCode
     * @return
     */
    int existUpdateOil(@Param("oilId") String oilId, @Param("oilCode") String oilCode);

    /**
     * 更新油卡信息
     *
     * @param oil
     * @return
     */
    int updateOil(@Param("oil") OilPojo oil);

    /**
     * 新增油卡
     *
     * @param oil
     * @return
     */
    int insertOil(@Param("oil") OilPojo oil);

}
