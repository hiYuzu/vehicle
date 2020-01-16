package com.plr.vehicle.controller;

import com.plr.vehicle.model.AuthorityModel;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.AuthorityPojo;
import com.plr.vehicle.service.IAuthorityService;
import com.plr.vehicle.service.IUserService;
import com.plr.vehicle.util.DateUtil;
import com.plr.vehicle.util.DefaultParam;
import com.plr.vehicle.util.IdWorkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping(value = "/AuthorityController")
public class AuthorityController {
    /**
     * 日志输出标记
     */
    private static final String LOG = "AuthorityController";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Resource
    private IAuthorityService authorityService;

    @Resource
    private IUserService userService;

    @Resource
    private IdWorkerUtil idWorker;

    @RequestMapping("/getAuthority")
    @ResponseBody
    public ResultListModel<AuthorityModel> getAuthority(AuthorityModel authorityModel, HttpSession httpSession) {
        ResultListModel<AuthorityModel> authorityResultList = new ResultListModel<>();
        AuthorityPojo authority = convertAuthority(authorityModel, httpSession);
        int count = authorityService.getAuthorityCount(authority);
        if (count > 0) {
            List<AuthorityModel> authorityModelList = new ArrayList<>();
            List<AuthorityPojo> authorityList = authorityService.getAuthority(authority);
            for (AuthorityPojo temp : authorityList) {
                temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
                AuthorityModel tempModel = convertAuthorityModel(temp);
                if (tempModel != null) {
                    authorityModelList.add(tempModel);
                }
            }
            authorityResultList.setData(authorityModelList);
        }
        authorityResultList.setCount(count);
        authorityResultList.setCode(DefaultParam.liSelectOk);
        return authorityResultList;
    }

    @RequestMapping("/deleteAuthority")
    @ResponseBody
    public ResultModel deleteAuthority(@RequestParam(value = "list[]") List<String> list, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        AuthorityPojo authorityTemp = authorityService.getAuthorityByUserId(Long.valueOf(loginUser.getUserId()));
        if (authorityTemp.getAuthorityCode().equals(DefaultParam.ADMINISTRATOR)) {
            if (list != null && list.size() > 0) {
                try {
                    int intResult = authorityService.deleteAuthority(list);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("权限组删除成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("权限组删除失败！");
                    }
                } catch (Exception e) {
                    resultModel.setResult(false);
                    resultModel.setDetail("权限组删除失败！");
                    logger.error(LOG + "：权限组删除失败，信息为：" + e.getMessage());
                }
            } else {
                resultModel.setResult(false);
                resultModel.setDetail("权限组删除失败，错误原因：服务器未接收到删除数据！");
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("权限组删除失败，失败原因：只有管理员才能操作权限组");
        }
        return resultModel;
    }

    /**
     * 更新权限组
     *
     * @param authorityModel
     * @param httpSession
     * @return
     */
    @RequestMapping("/updateAuthority")
    @ResponseBody
    public ResultModel updateAuthority(AuthorityModel authorityModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        AuthorityPojo authorityTemp = authorityService.getAuthorityByUserId(Long.valueOf(loginUser.getUserId()));
        if (authorityTemp.getAuthorityCode().equals(DefaultParam.ADMINISTRATOR)) {
            if (authorityModel != null) {
                try {
                    AuthorityPojo authority = convertAuthority(authorityModel, httpSession);
                    if (authorityService.existUpdateAuthority(authority.getAuthorityId(), authority.getAuthorityCode()) == 0) {
                        int intResult = authorityService.updateAuthority(authority);
                        if (intResult > 0) {
                            resultModel.setResult(true);
                            resultModel.setDetail("权限组更新成功！");
                        } else {
                            resultModel.setResult(false);
                            resultModel.setDetail("权限组更新失败！");
                        }
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("系统已存在此权限组编号，请更换！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                resultModel.setResult(false);
                resultModel.setDetail("没有可以操作的数据！");
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("权限组更新失败，失败原因：只有管理员才能操作权限组");
        }
        return resultModel;
    }


    @RequestMapping("/insertAuthority")
    @ResponseBody
    public ResultModel insertAuthority(AuthorityModel authorityModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        AuthorityPojo authorityTemp = authorityService.getAuthorityByUserId(Long.valueOf(loginUser.getUserId()));
        if (authorityTemp.getAuthorityCode().equals(DefaultParam.ADMINISTRATOR)) {
            if (authorityModel != null) {
                try {
                    AuthorityPojo authority = convertAuthority(authorityModel, httpSession);
                    authority.setAuthorityId(idWorker.nextId());
                    if (authorityService.existUpdateAuthority(authority.getAuthorityId(), authority.getAuthorityCode()) == 0) {
                        int intResult = authorityService.insertAuthority(authority);
                        if (intResult > 0) {
                            resultModel.setResult(true);
                            resultModel.setDetail("权限组添加成功！");
                        } else {
                            resultModel.setResult(false);
                            resultModel.setDetail("权限组添加失败！");
                        }
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("系统已存在此权限组编号，请使用其他编号！");
                    }
                } catch (Exception e) {
                    resultModel.setResult(false);
                    resultModel.setDetail("权限组添加失败！");
                    logger.error(LOG + "：权限组添加失败，信息为：" + e.getMessage());
                }
            } else {
                resultModel.setResult(false);
                resultModel.setDetail("没有可以操作的数据！");
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("权限组更新失败，失败原因：只有管理员才能操作权限组");
        }
        return resultModel;
    }

    /**
     * 查询是否具有指定页面的查看权限
     *
     * @param pageId      页面id
     * @param httpSession 登陆用户信息
     * @return 有权限：返回pageUrl
     * 无权限：返回msg
     */
    @RequestMapping("isAuthorityPage")
    @ResponseBody
    public ResultModel isAuthorityPage(@RequestParam(value = "pageId") long pageId, HttpSession httpSession) {
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        long authorityId = authorityService.getAuthorityByUserId(Long.valueOf(loginUser.getUserId())).getAuthorityId();
        String pageUrl = authorityService.isAuthorityPage(authorityId, pageId);
        return ResultModel.getInstance(true, pageUrl);
    }

    /**
     * AuthorityModel转Authority
     *
     * @param authorityModel
     * @param httpSession
     * @return
     */
    private AuthorityPojo convertAuthority(AuthorityModel authorityModel, HttpSession httpSession) {
        AuthorityPojo authority = new AuthorityPojo();
        if (authorityModel != null) {
            try {
                if (authorityModel.getAuthorityId() != null && !authorityModel.getAuthorityId().isEmpty()) {
                    authority.setAuthorityId(Long.valueOf(authorityModel.getAuthorityId()));
                }
                authority.setAuthorityCode(authorityModel.getAuthorityCode());
                authority.setAuthorityName(authorityModel.getAuthorityName());
                authority.setAuthorityRemark(authorityModel.getAuthorityRemark());
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                if (loginUser != null && loginUser.getUserId() != null && !loginUser.getUserId().isEmpty()) {
                    authority.setOptUser(Long.valueOf(loginUser.getUserId()));
                }
                if (authorityModel.getOptTime() != null && !authorityModel.getOptTime().isEmpty()) {
                    authority.setOptTime(DateUtil.StringToTimestamp(authorityModel.getOptTime(), DateUtil.DATA_TIME_SECOND));
                } else {
                    authority.setOptTime(DateUtil.GetSystemDateTime(0));
                }
                authority.setRowCount(authorityModel.getRowCount());
                authority.setRowIndex(authorityModel.getRowIndex());
            } catch (Exception e) {
                logger.error(LOG + "：将AuthorityModel转换成AuthorityPojo失败，信息为" + e.getMessage());
            }
        }
        return authority;
    }

    /**
     * Authority转AuthorityModel
     *
     * @param authority
     * @return
     */
    private AuthorityModel convertAuthorityModel(AuthorityPojo authority) {
        AuthorityModel authorityModel = new AuthorityModel();
        if (authority != null) {
            authorityModel.setAuthorityId(String.valueOf(authority.getAuthorityId()));
            authorityModel.setAuthorityCode(authority.getAuthorityCode());
            authorityModel.setAuthorityName(authority.getAuthorityName());
            authorityModel.setAuthorityRemark(authority.getAuthorityRemark());
            authorityModel.setOptUser(String.valueOf(authority.getOptUser()));
            authorityModel.setOptUserName(authority.getOptUserName());
            authorityModel.setOptTime(DateUtil.TimestampToString(authority.getOptTime(), DateUtil.DATA_TIME_SECOND));
        }
        return authorityModel;
    }

}
