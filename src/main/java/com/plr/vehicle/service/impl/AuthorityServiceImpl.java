package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAuthorityDao;
import com.plr.vehicle.pojo.AuthorityPojo;
import com.plr.vehicle.service.IAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
@Service("authorityService")
public class AuthorityServiceImpl implements IAuthorityService {
    @Resource
    private IAuthorityDao authorityDao;

    @Override
    public AuthorityPojo getAuthorityByUserId(long userId) {
        return authorityDao.getAuthorityByUserId(userId);
    }

    @Override
    public int getAuthorityCount(AuthorityPojo authority) {
        return authorityDao.getAuthorityCount(authority);
    }

    @Override
    public List<AuthorityPojo> getAuthority(AuthorityPojo authority) {
        return authorityDao.getAuthority(authority);
    }

    @Override
    public int deleteAuthority(List<String> list) {
        return authorityDao.deleteAuthority(list);
    }

    @Override
    public int existUpdateAuthority(long authorityId, String authorityCode) {
        return authorityDao.existUpdateAuthority(authorityId, authorityCode);
    }

    @Override
    public int updateAuthority(AuthorityPojo authority) {
        return authorityDao.updateAuthority(authority);
    }

    @Override
    public int insertAuthority(AuthorityPojo authority) {
        return authorityDao.insertAuthority(authority);
    }

    @Override
    public String isAuthorityPage(long authorityId, long pageId) {
        String pageUrl = authorityDao.isAuthorityPage(authorityId, pageId);
        return pageUrl == null ? "" : pageUrl;
    }

    @Override
    public List<String> getAuthorityIdsByUserId(long userId) {
        return authorityDao.getAuthorityIdsByUserId(userId);
    }

    @Override
    public String getAuthorityNameById(List<String> authorityIds) {
        List<String> authorityNames = authorityDao.getAuthorityNameById(authorityIds);
        StringBuilder stringBuilder = new StringBuilder();
        for (String temp : authorityNames) {
            stringBuilder.append(temp);
            stringBuilder.append("、");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("、"));
        return stringBuilder.toString();
    }
}
