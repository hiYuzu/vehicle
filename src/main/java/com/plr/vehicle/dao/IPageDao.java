package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.PagePojo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IPageDao {

    /**
     * 获取权限页面
     * @param pageIdList
     * @return
     */
    List<PagePojo> getPage(@Param("pageIdList") List<Integer> pageIdList);

    /**
     * 获取所有页面
     * @return 为null返回size() = 0
     */
    List<PagePojo> getAllPage();
}
