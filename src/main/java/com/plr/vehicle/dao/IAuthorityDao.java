package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.AuthorityPojo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
public interface IAuthorityDao {
    /**
     * 查询权限组信息
     *
     * @param userId
     * @return
     */
    AuthorityPojo getAuthorityByUserId(@Param("userId") long userId);

    /**
     * 查询权限组数量
     *
     * @param authority
     * @return
     */
    int getAuthorityCount(@Param("authority") AuthorityPojo authority);

    /**
     * 查询权限组信息
     *
     * @param authority
     * @return
     */
    List<AuthorityPojo> getAuthority(@Param("authority") AuthorityPojo authority);

    /**
     * 删除权限组
     *
     * @param list
     * @return
     */
    int deleteAuthority(@Param("list") List<String> list);

    /**
     * 查询是否存在权限组
     *
     * @param authorityId
     * @param authorityCode
     * @return
     */
    int existUpdateAuthority(@Param("authorityId") long authorityId, @Param("authorityCode") String authorityCode);

    /**
     * 更新权限组
     *
     * @param authority
     * @return
     * @throws Exception
     */
    int updateAuthority(@Param("authority") AuthorityPojo authority);

    /**
     * 添加权限组
     *
     * @param authority
     * @return
     */
    int insertAuthority(@Param("authority") AuthorityPojo authority);

    /**
     * 查询是否具有指定页面的查看权限
     *
     * @param authorityId 权限组id
     * @param pageId      页面id
     * @return String
     */
    String isAuthorityPage(@Param("authorityId") long authorityId, @Param("pageId") long pageId);

    /**
     * userId=>authorityId
     *
     * @param userId
     * @return
     */
    List<String> getAuthorityIdsByUserId(@Param("userId") long userId);

    /**
     * authorityIds => authorityNames
     *
     * @param authorityIds
     * @return list
     */
    List<String> getAuthorityNameById(@Param("authorityIds") List<String> authorityIds);
}
