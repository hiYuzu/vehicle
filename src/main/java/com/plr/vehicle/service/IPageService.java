package com.plr.vehicle.service;

import com.plr.vehicle.pojo.PagePojo;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IPageService {

    /**
     * 获取页面
     * @param pageIdList
     * @return
     */
    List<PagePojo> getPage(List<Integer> pageIdList);

    /**
     * 获取所有页面
     * @return 为null返回size() = 0
     */
    List<PagePojo> getAllPage();
}
