package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAuthorityDetailDao;
import com.plr.vehicle.pojo.AuthorityDetailPojo;
import com.plr.vehicle.service.IAuthorityDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("authorityDetailService")
@Transactional(rollbackFor = Exception.class)
public class AuthorityDetailServiceImpl implements IAuthorityDetailService {
    @Resource
    private IAuthorityDetailDao authorityDetailDao;

    @Override
    public List<Integer> getPageIdList(List<String> authorityIds) {
        return authorityDetailDao.getPageIdList(authorityIds);
    }

    @Override
    public int revokePagePermission(long authorityId) {
        return authorityDetailDao.revokePagePermission(authorityId);
    }

    @Override
    public int getAuthorityDetailCount(AuthorityDetailPojo authorityDetail) {
        return authorityDetailDao.getAuthorityDetailCount(authorityDetail);
    }

    @Override
    public void updateAuthorityDetail(AuthorityDetailPojo authorityDetail) {
        authorityDetailDao.updateAuthorityDetail(authorityDetail);
    }

    @Override
    public void insertAuthorityDetail(AuthorityDetailPojo authorityDetail) {
        authorityDetailDao.insertAuthorityDetail(authorityDetail);
    }
}
