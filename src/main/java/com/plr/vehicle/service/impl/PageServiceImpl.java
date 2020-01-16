package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IPageDao;
import com.plr.vehicle.pojo.PagePojo;
import com.plr.vehicle.service.IPageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("pageService")
@Transactional(rollbackFor = Exception.class)
public class PageServiceImpl implements IPageService {

    @Resource
    private IPageDao pageDao;

    @Override
    public List<PagePojo> getPage(List<Integer> pageIdList) {
        return pageDao.getPage(pageIdList);
    }

    @Override
    public List<PagePojo> getAllPage() {
        List<PagePojo> result = pageDao.getAllPage();
        return result == null ? new ArrayList<>() : result;
    }
}
