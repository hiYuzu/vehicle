package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.INewsDao;
import com.plr.vehicle.pojo.NewsPojo;
import com.plr.vehicle.service.INewsService;
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
@Service("newsService")
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements INewsService {

    @Resource
    private INewsDao newsDao;

    @Override
    public int getNewsCount(NewsPojo newsPojo) {
        return newsDao.getNewsCount(newsPojo);
    }

    @Override
    public List<NewsPojo> getNews(NewsPojo newsPojo) {
        return newsDao.getNews(newsPojo, false);
    }

    @Override
    public int insertNews(NewsPojo newsPojo) throws Exception {
        return newsDao.insertNews(newsPojo);
    }

    @Override
    public int updateNews(NewsPojo newsPojo) throws Exception {
        return newsDao.updateNews(newsPojo);
    }

    @Override
    public int deleteNews(List<String> idList) throws Exception {
        return newsDao.deleteNews(idList);
    }

    @Override
    public List<NewsPojo> showNews() {
        List<NewsPojo> newsList = newsDao.getNews(new NewsPojo(), true);
        return newsList == null ? new ArrayList<>() : newsList;
    }
}
