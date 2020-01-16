package com.plr.vehicle.service;

import com.plr.vehicle.pojo.NewsPojo;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface INewsService {

    /**
     * 查询公告个数
     * @param newsPojo
     * @return
     */
    int getNewsCount(NewsPojo newsPojo);

    /**
     * 查询公告
     * @param newsPojo
     * @return
     */
    List<NewsPojo> getNews(NewsPojo newsPojo);

    /**
     * 新增公告
     * @param newsPojo
     * @return
     */
    int insertNews(NewsPojo newsPojo) throws Exception;

    /**
     * 更新公告
     * @param newsPojo
     * @return
     */
    int updateNews(NewsPojo newsPojo) throws Exception;

    /**
     * 删除公告
     * @param idList
     * @return
     */
    int deleteNews(List<String> idList) throws Exception;

    /**
     * 首页显示公告
     * @return list
     */
    List<NewsPojo> showNews();

}
