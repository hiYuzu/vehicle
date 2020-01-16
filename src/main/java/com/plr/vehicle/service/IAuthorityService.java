package com.plr.vehicle.service;

import com.plr.vehicle.pojo.AuthorityPojo;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
public interface IAuthorityService {

    /**
     * 通过userId查询权限组信息
     * @param userId
     * @return
     */
    AuthorityPojo getAuthorityByUserId(long userId);

    /**
     * 查询权限组数量
     * @param authority
     * @return
     */
    int getAuthorityCount(AuthorityPojo authority);

    /**
     * 查询权限组信息
     * @param authority
     * @return
     */
    List<AuthorityPojo> getAuthority(AuthorityPojo authority);

    /**
     * 删除权限组
     * @param list
     * @return
     * @throws Exception
     */
    int deleteAuthority(List<String> list) throws Exception;

    /**
     * 查询是否存在权限组
     * @param authorityId
     * @param authorityCode
     * @return
     */
    int existUpdateAuthority(long authorityId, String authorityCode);

    /**
     * 更新权限组
     * @param authority
     * @return
     * @throws Exception
     */
    int updateAuthority(AuthorityPojo authority) throws Exception;

    /**
     * 添加权限组
     * @param authority
     * @return
     */
    int insertAuthority(AuthorityPojo authority);

    /**
     * 查询是否具有指定页面的查看权限
     *
     * @param authorityId 权限组id
     * @param pageId 页面id
     * @return
     *
     * 有权限：返回pageUrl
     * 无权限：返回null
     */
    String isAuthorityPage(long authorityId, long pageId);

    /**
     * userId=>authorityId
     * @param userId
     * @return
     */
    List<String> getAuthorityIdsByUserId(long userId);

    /**
     * authorityIds => authorityName(拼接)
     * @param authorityIds
     * @return
     */
    String getAuthorityNameById(List<String> authorityIds);
}
