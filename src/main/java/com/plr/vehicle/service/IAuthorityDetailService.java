package com.plr.vehicle.service;

import com.plr.vehicle.pojo.AuthorityDetailPojo;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IAuthorityDetailService {

    /**
     * 获取权限内所有pageId
     * @param authorityIds
     * @return
     */
    List<Integer> getPageIdList(List<String> authorityIds);

    /**
     * 撤销权限组所有页面查看权限
     * @param authorityId
     * @return
     */
    int revokePagePermission(long authorityId);

    /**
     * 获取权限详情数量
     * @param authorityDetail
     * @return
     */
    int getAuthorityDetailCount(AuthorityDetailPojo authorityDetail);

    /**
     * 更新AuthorityDetail
     * @param authorityDetail
     */
    void updateAuthorityDetail(AuthorityDetailPojo authorityDetail);

    /**
     * 插入AuthorityDetail
     * @param authorityDetail
     */
    void insertAuthorityDetail(AuthorityDetailPojo authorityDetail);
}
