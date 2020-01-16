package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.NewsPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface INewsDao {

    /**
     * 查询公告个数
     * @param newsPojo
     * @return
     */
    int getNewsCount(@Param("newsPojo") NewsPojo newsPojo);

    /**
     * 查询公告
     * @param newsPojo
     * @param showNewsFlag
     * @return
     */
    List<NewsPojo> getNews(@Param("newsPojo") NewsPojo newsPojo, @Param("showNewsFlag") boolean showNewsFlag);

    /**
     * 新增公告
     * @param newsPojo
     * @return
     */
    int insertNews(@Param("newsPojo") NewsPojo newsPojo);

    /**
     * 更新公告
     * @param newsPojo
     * @return
     */
    int updateNews(@Param("newsPojo") NewsPojo newsPojo);

    /**
     * 删除公告
     * @param idList
     * @return
     */
    int deleteNews(@Param("idList") List<String> idList);


}
