package com.plr.vehicle.controller;

import com.plr.vehicle.config.SystemConfig;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.UserPojo;
import com.plr.vehicle.service.IAuthorityService;
import com.plr.vehicle.service.IUserService;
import com.plr.vehicle.util.DateUtil;
import com.plr.vehicle.util.DefaultParam;
import com.plr.vehicle.util.IdWorkerUtil;
import com.plr.vehicle.util.ShaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:42
 */
@Controller
@RequestMapping("/UserController")
public class UserController {

    /**
     * 日志输出标记
     */
    private static final String LOG = "UserController";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @Resource
    private IAuthorityService authorityService;

    @Resource
    private IdWorkerUtil idWorker;

    @Resource
    private SystemConfig systemConfig;

    /**
     * 获取登录用户信息
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/getLoginUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public UserModel getLoginUser(HttpSession httpSession) {
        return (UserModel) httpSession.getAttribute(httpSession.getId());
    }

    /**
     * 获取用户信息数据
     *
     * @param userModel
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultListModel<UserModel> getUser(UserModel userModel, HttpSession httpSession, @RequestParam("userDelete") int userDelete) {
        List<UserModel> userModelList = new ArrayList<UserModel>();
        List<UserPojo> userList;
        userModel.setUserDelete(userDelete == 1);
        UserPojo userPojo = convertUserPojo(userModel, true, httpSession);
        int count = userService.getUserCount(userPojo);
        if (count > 0) {
            userList = userService.getUser(userPojo);
            for (UserPojo temp : userList) {
                temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
                UserModel userModelTemp = convertUserModel(temp);
                if (userModelTemp != null) {
                    String authorityName = authorityService.getAuthorityNameById(authorityService.getAuthorityIdsByUserId(temp.getUserId()));
                    userModelTemp.setAuthorityName(authorityName);
                    userModelList.add(userModelTemp);
                }
            }
        }
        return new ResultListModel<>(count, userModelList, null);
    }

    @RequestMapping("/getAccessUser")
    @ResponseBody
    public ResultListModel<UserModel> getAccessUser(String authorityId, UserModel userModel, HttpSession httpSession) {
        UserPojo user = convertUserPojo(userModel, true, httpSession);
        List<UserPojo> userListSub = userService.getAccessUser(user, Long.valueOf(authorityId));
        List<UserPojo> userList = userService.getUser(user);
        List<UserModel> userModelList = new ArrayList<>();
        for (UserPojo temp : userList) {
            temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
            UserModel userModelTemp = convertUserModel(temp);
            if (userListSub != null) {
                if (userListSub.contains(temp)) {
                    userModelTemp.setChecked(true);
                }
            }
            if (userModelTemp != null) {
                userModelList.add(userModelTemp);
            }
        }
        return new ResultListModel<>(userModelList.size(), userModelList, null);
    }

    /**
     * 解锁盐城
     *
     * @param passWord
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/unlockScreen", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel unlockScreen(String passWord, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        try {
            String unlockPwd = ShaUtil.shaEncode(passWord);
            UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
            if (loginUser == null) {
                resultModel.setResult(false);
                resultModel.setDetail("未登录或超时，请重新登录！");
            } else if (!loginUser.getUserPassword().equals(unlockPwd)) {
                resultModel.setResult(false);
                resultModel.setDetail("密码不正确！");
            } else {
                resultModel.setResult(true);
                resultModel.setDetail("验证成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    /**
     * 新增用户
     *
     * @param userModel
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/insertUser", method = {RequestMethod.POST})
    public @ResponseBody
    ResultModel insertUser(UserModel userModel, HttpSession httpSession, @RequestParam("userDelete") int userDelete) {
        ResultModel resultModel = new ResultModel();
        if (userModel != null) {
            try {
                UserPojo userPojo = new UserPojo();
                userPojo.setUserCode(userModel.getUserCode());
                if (userService.getUserCount(userPojo) == 0) {
                    userModel.setUserPassword(DefaultParam.PWD_DEFAULT);
                    userModel.setUserDelete(userDelete == 1);
                    userPojo = convertUserPojo(userModel, true, httpSession);
                    userPojo.setUserId(idWorker.nextId());
                    int intResult = userService.insertUser(userPojo);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("新增用户成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("新增用户失败！");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此用户，请使用其他注册账号！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("新增用户失败！");
                logger.error(LOG + "：新增用户失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    /**
     * 更新用户
     *
     * @param userModel
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST})
    public @ResponseBody
    ResultModel updateUser(UserModel userModel, HttpSession httpSession, @RequestParam("userDelete") int userDelete) {
        ResultModel resultModel = new ResultModel();
        if (userModel != null) {
            try {
                if (userService.existUpdateUser(userModel.getUserId(), userModel.getUserCode()) == 0) {
                    userModel.setUserDelete(userDelete == 1);
                    UserPojo userPojo = convertUserPojo(userModel, false, httpSession);
                    int intResult = userService.updateUser(userPojo);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("更新用户成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("更新用户失败！");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此用户编码，请更换！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("更新用户失败！");
                logger.error(LOG + "：更新用户失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    /**
     * 更新用户密码
     *
     * @param oldPwd
     * @param newPwd
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateUserPassword", method = {RequestMethod.POST})
    public @ResponseBody
    ResultModel updateUserPassword(String oldPwd, String newPwd, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (!newPwd.isEmpty() && !oldPwd.isEmpty()) {
            try {
                newPwd = ShaUtil.shaEncode(newPwd);
                oldPwd = ShaUtil.shaEncode(oldPwd);
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                if (loginUser == null) {
                    resultModel.setResult(false);
                    resultModel.setDetail("未登录，不能进行密码修改！");
                } else if (!loginUser.getUserPassword().equals(oldPwd)) {
                    resultModel.setResult(false);
                    resultModel.setDetail("密码不正确！");
                } else if (loginUser.getUserPassword().equals(newPwd)) {
                    resultModel.setResult(false);
                    resultModel.setDetail("新密码与原密码一致！");
                } else {
                    int intResult = userService.updateUserPassword(loginUser.getUserId(), newPwd);
                    if (intResult > 0) {
                        ((UserModel) httpSession.getAttribute(httpSession.getId())).setUserPassword(newPwd);
                        resultModel.setResult(true);
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("更新用户失败！");
                    }
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("更新用户失败！");
                logger.error(LOG + "：更新用户失败，信息为：" + e.getMessage());
            }
        }
        return resultModel;
    }

    /**
     * 删除用户
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "/deleteUsers", method = {RequestMethod.POST})
    public @ResponseBody
    ResultModel deleteUsers(@RequestParam(value = "list[]") List<String> list) {
        ResultModel resultModel = new ResultModel();
        if (list != null && list.size() > 0) {
            try {
                List<String> listId = new ArrayList<String>();
                for (String userId : list) {
                    String userCode = userService.getUserCodeById(userId);
                    if (!systemConfig.getRootUser().equals(userCode)) {
                        listId.add(userId);
                    }
                }
                int intResult = userService.deleteUsers(listId);
                if (intResult > 0) {
                    resultModel.setResult(true);
                    resultModel.setDetail("删除用户成功！");
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("删除用户失败,检查用户是否已登录！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("删除用户失败！");
                logger.error(LOG + "：删除用户失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("删除用户失败，错误原因：服务器未接收到删除数据！");
        }
        return resultModel;
    }

    /**
     * 将UserPojo转换为UserModel
     *
     * @param userPojo
     * @return
     */
    public UserModel convertUserModel(UserPojo userPojo) {
        UserModel userModel = new UserModel();
        if (userPojo != null) {
            userModel.setUserId(String.valueOf(userPojo.getUserId()));
            userModel.setUserCode(userPojo.getUserCode());
            userModel.setUserPassword(userPojo.getUserPassword());
            userModel.setUserName(userPojo.getUserName());
            userModel.setUserTel(userPojo.getUserTel());
            userModel.setUserEmail(userPojo.getUserEmail());
            userModel.setUserDelete(userPojo.isUserDelete());
            userModel.setUserDeleteName("否");
            if (userModel.isUserDelete()) {
                userModel.setUserDeleteName("是");
            }
            userModel.setUserRemark(userPojo.getUserRemark());
            userModel.setOptUser(String.valueOf(userPojo.getOptUser()));
            userModel.setOptUserName(userPojo.getOptUserName());
            userModel.setOptTime(DateUtil.TimestampToString(userPojo.getOptTime(), DateUtil.DATA_TIME_SECOND));
        }
        return userModel;
    }

    /**
     * 将UserModel转换为UserPojo
     *
     * @param userModel
     * @param encryFlag
     * @param httpSession
     * @return
     */
    public UserPojo convertUserPojo(UserModel userModel, boolean encryFlag, HttpSession httpSession) {
        UserPojo userPojo = new UserPojo();
        if (userModel != null) {
            try {
                if (userModel.getUserId() != null && !userModel.getUserId().isEmpty()) {
                    userPojo.setUserId(Long.valueOf(userModel.getUserId()));
                }
                userPojo.setUserCode(userModel.getUserCode());
                if (userModel.getUserPassword() != null) {
                    if (encryFlag) {
                        userPojo.setUserPassword(ShaUtil.shaEncode(userModel.getUserPassword()));
                    } else {
                        userPojo.setUserPassword(userModel.getUserPassword());
                    }
                }
                userPojo.setUserName(userModel.getUserName());
                userPojo.setUserTel(userModel.getUserTel());
                userPojo.setUserDelete(userModel.isUserDelete());
                userPojo.setUserEmail(userModel.getUserEmail());
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                if (loginUser != null && loginUser.getUserId() != null && !loginUser.getUserId().isEmpty()) {
                    userPojo.setOptUser(Long.valueOf(loginUser.getUserId()));
                }
                userPojo.setUserRemark(userModel.getUserRemark());
                if (userModel.getOptTime() != null && !userModel.getOptTime().isEmpty()) {
                    userPojo.setOptTime(DateUtil.StringToTimestamp(userModel.getOptTime(), DateUtil.DATA_TIME_SECOND));
                } else {
                    userPojo.setOptTime(DateUtil.GetSystemDateTime(0));
                }
                userPojo.setRowCount(userModel.getRowCount());
                userPojo.setRowIndex(userModel.getRowIndex());
            } catch (Exception e) {
                logger.error(LOG + "：将UserModel转换成UserPojo失败，信息为" + e.getMessage());
            }
        }
        return userPojo;
    }

}