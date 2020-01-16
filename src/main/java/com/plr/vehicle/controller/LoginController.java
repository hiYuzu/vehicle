package com.plr.vehicle.controller;

import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.UserPojo;
import com.plr.vehicle.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:22
 */
@Controller
public class LoginController {
    /**
     * 日志输出标记
     */
    private static final String LOG = "LoginController";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private IUserService userService;

    @Resource
    private UserController userController;

    @RequestMapping(value = "/")
    public String index() {
        return "html/login";
    }

    @RequestMapping(value = "/login")
    public String login(UserModel userModel, HttpSession httpSession) {
        ResultModel resultModel = validateLogin(userModel, httpSession);
        if (resultModel.getResult()) {
            UserModel sessionUser;
            UserPojo userPojo = userController.convertUserPojo(userModel, true, httpSession);
            List<UserPojo> listUserPojo = userService.getUser(userPojo);
            if (listUserPojo != null && listUserPojo.size() > 0) {
                sessionUser = userController.convertUserModel(listUserPojo.get(0));
                httpSession.setAttribute(httpSession.getId(), sessionUser);
                return "html/index";
            } else {
                return "html/login";
            }
        } else {
            return "html/login";
        }
    }

    @RequestMapping(value = "/validateLogin", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel validateLogin(UserModel userModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (userModel != null && userModel.getUserCode() != null && !userModel.getUserCode().isEmpty()
                && userModel.getUserPassword() != null && !userModel.getUserPassword().isEmpty()) {
            try {
                UserPojo userPojo = userController.convertUserPojo(userModel, true, httpSession);
                int count = userService.getUserCount(userPojo);
                if (count > 0) {
                    resultModel.setResult(true);
                    resultModel.setDetail("登录验证成功");
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("用户名或密码验证失败");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("登录验证异常，异常信息：" + e.getMessage());
                logger.error(LOG + ":登录验证异常，异常信息：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("登录验证信息为空");
        }
        return resultModel;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResultModel logout(@RequestParam(value = "userCode", required = false) String userCode, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        resultModel.setResult(true);
        resultModel.setDetail("退出系统成功");
        return resultModel;
    }

    @RequestMapping(value = "/login404")
    public String login404() {
        return "html/404";
    }
}
