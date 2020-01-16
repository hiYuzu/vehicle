package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAuthorityUserDao;
import com.plr.vehicle.pojo.AuthorityUserPojo;
import com.plr.vehicle.service.IAuthorityUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
@Service("authorityUserService")
@Transactional(rollbackFor = Exception.class)
public class AuthorityUserServiceImpl implements IAuthorityUserService {
    /**
     * 日志
     */
    private static final String LOG = "AuthorityUserServiceImpl";
    private Logger logger = LoggerFactory.getLogger(AuthorityUserServiceImpl.class);

    @Resource
    private IAuthorityUserDao authorityUserDao;

    @Override
    public void revokeUserPermission(long authorityId, long userId) {
        if (authorityId > 0 || userId > 0) {
            authorityUserDao.revokeUserPermission(authorityId, userId);
        } else {
            logger.error(LOG + "：错误！权限组id和用户id同时不存在会导致用户权限关系表清空！");
        }
    }

    @Override
    public void insertAuthorityUser(AuthorityUserPojo authorityUser) {
        //验证是否已存在此权限关系
        if (authorityUserDao.isExist(authorityUser) == 0) {
            authorityUserDao.insertAuthorityUser(authorityUser);
        }
    }
}
