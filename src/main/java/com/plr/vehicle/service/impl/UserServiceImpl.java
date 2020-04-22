package com.plr.vehicle.service.impl;

import com.plr.vehicle.dao.IAuthorityUserDao;
import com.plr.vehicle.dao.IUserDao;
import com.plr.vehicle.pojo.AuthorityPojo;
import com.plr.vehicle.pojo.AuthorityUserPojo;
import com.plr.vehicle.pojo.UserPojo;
import com.plr.vehicle.service.IUserService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Resource
    private IAuthorityUserDao authorityUserDao;

    @Override
    public int getUserCount(UserPojo userPojo) {
        return userDao.getUserCount(userPojo);
    }

    @Override
    public List<UserPojo> getUser(UserPojo userPojo) {
        return userDao.getUser(userPojo);
    }

    @Override
    public List<UserPojo> getAccessUser(UserPojo user, long authorityId) {
        return userDao.getAccessUser(user, authorityId);
    }

    @Override
    public int insertUser(UserPojo userPojo) throws Exception {
        int result = 0;
        if(userPojo != null){
            result = userDao.insertUser(userPojo);
            AuthorityUserPojo au = new AuthorityUserPojo();
            au.setAuthorityUserId(userPojo.getUserId() + 1);
            au.setAuthority(new AuthorityPojo());
            au.getAuthority().setAuthorityId(2);
            au.setUser(userPojo);
            au.setOptUser(userPojo.getOptUser());
            authorityUserDao.insertAuthorityUser(au);
        }

        return result;
    }

    @Override
    public int updateUser(UserPojo userPojo) throws Exception {
        return userDao.updateUser(userPojo);
    }

    @Override
    public int updateUserPassword(String userId, String userPassword) throws Exception {
        return userDao.updateUserPassword(userId,userPassword);
    }

    @Override
    public int deleteUsers(List<String> idList) throws Exception {
        return userDao.deleteUsers(idList);
    }

    @Override
    public int existUpdateUser(String userId, String userCode) {
        return userDao.existUpdateUser(userId, userCode);
    }

    @Override
    public String getUserCodeById(String userId) {
        return userDao.getUserCodeById(userId);
    }

    @Override
    public String getUserNameById(long userId) {
        return userDao.getUserNameById(userId);
    }

}
