package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.AuthorityDetailPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IAuthorityDetailDao {
    /**
     * 获取权限内所有pageId
     * @param authorityIds
     * @return
     */
    List<Integer> getPageIdList(@Param("authorityIds") List<String> authorityIds);

    /**
     * 撤销权限组所有页面查看权限
     * @param authorityId
     * @return
     */
    int revokePagePermission(@Param("authorityId") long authorityId);

    /**
     * 获取权限详情数量
     * @param authorityDetail
     * @return
     */
    int getAuthorityDetailCount(@Param("authorityDetail") AuthorityDetailPojo authorityDetail);

    /**
     * 更新AuthorityDetail
     * @param authorityDetail
     */
    void updateAuthorityDetail(@Param("authorityDetail") AuthorityDetailPojo authorityDetail);

    /**
     * 插入AuthorityDetail
     * @param authorityDetail
     */
    void insertAuthorityDetail(@Param("authorityDetail") AuthorityDetailPojo authorityDetail);
}
