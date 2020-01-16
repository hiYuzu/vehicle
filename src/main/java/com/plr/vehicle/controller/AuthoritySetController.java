package com.plr.vehicle.controller;

import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.*;
import com.plr.vehicle.service.IAccessVehicleService;
import com.plr.vehicle.service.IAccessOilService;
import com.plr.vehicle.service.IAuthorityDetailService;
import com.plr.vehicle.service.IAuthorityUserService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping("/AuthoritySetController")
public class AuthoritySetController {
    /**
     * 日志
     */
    private static final String LOG = "AuthoritySetController";
    private Logger logger = LoggerFactory.getLogger(AuthoritySetController.class);

    /**
     * 服务
     */
    @Resource
    private IAuthorityDetailService authorityDetailService;

    @Resource
    private IdWorkerUtil idWorker;

    @Resource
    private IAccessVehicleService accessVehicleService;

    @Resource
    private IAccessOilService accessOilService;

    @Resource
    private IAuthorityUserService authorityUserService;

    /**
     * 修改配置页面权限
     *
     * @param authorityId
     * @param dataArray
     * @param httpSession
     * @return
     */
    @RequestMapping("/updatePagePower")
    @ResponseBody
    public ResultModel updatePagePower(@RequestParam(value = "authorityId") long authorityId, @RequestParam(value = "dataArray[]") List<String> dataArray, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        try {
            UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
            int revokeResult = authorityDetailService.revokePagePermission(authorityId);
            if (revokeResult > 0) {
                AuthorityDetailPojo authorityDetail = new AuthorityDetailPojo();
                authorityDetail.setAuthority(new AuthorityPojo());
                authorityDetail.setOptUser(Long.valueOf(loginUser.getUserId()));
                authorityDetail.setCheckStatus("checked");
                authorityDetail.setDealType(1);
                for (String controlId : dataArray) {
                    authorityDetail.setControlId(Long.valueOf(controlId));
                    authorityDetail.getAuthority().setAuthorityId(authorityId);
                    int count = authorityDetailService.getAuthorityDetailCount(authorityDetail);
                    if (count > 0) {
                        authorityDetailService.updateAuthorityDetail(authorityDetail);
                    } else {
                        authorityDetail.setAuthorityDetailId(idWorker.nextId());
                        authorityDetailService.insertAuthorityDetail(authorityDetail);
                    }
                }
            }
            resultModel.setResult(true);
        } catch (Exception e) {
            resultModel.setResult(false);
            resultModel.setDetail("更新失败！");
            logger.error(LOG + "：更新失败，信息为：" + e.getMessage());
        }
        return resultModel;
    }

    /**
     * 更新配置车辆权限
     *
     * @param authorityId
     * @param dataArray
     * @param httpSession
     * @return
     */
    @RequestMapping("/updateVehiclePower")
    @ResponseBody
    public ResultModel updateVehiclePower(@RequestParam(value = "authorityId") long authorityId, @RequestParam(required = false, value = "dataArray[]") List<String> dataArray, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        try {
            accessVehicleService.revokeVehiclePermission(authorityId);
            if (dataArray != null && !dataArray.isEmpty()) {
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                AccessVehiclePojo accessVehicle = new AccessVehiclePojo();
                accessVehicle.setAuthority(new AuthorityPojo());
                accessVehicle.setVehicle(new VehiclePojo());
                accessVehicle.setOptUser(Long.valueOf(loginUser.getUserId()));
                for (String vehicleId : dataArray) {
                    accessVehicle.getVehicle().setVehicleId(Long.valueOf(vehicleId));
                    accessVehicle.getAuthority().setAuthorityId(authorityId);
                    accessVehicle.setAccessVehicleId(idWorker.nextId());
                    accessVehicleService.insertAccessVehicle(accessVehicle);
                }
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            } else {
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            }
        } catch (Exception e) {
            resultModel.setResult(false);
            resultModel.setDetail("更新失败！");
            logger.error(LOG + "：更新失败，信息为：" + e.getMessage());
        }
        return resultModel;
    }

    /**
     * 更新配置油卡权限
     *
     * @param authorityId
     * @param dataArray
     * @param httpSession
     * @return
     */
    @RequestMapping("/updateOilPower")
    @ResponseBody
    public ResultModel updateOilPower(@RequestParam(value = "authorityId") long authorityId, @RequestParam(required = false, value = "dataArray[]") List<String> dataArray, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        try {
            accessOilService.revokeOilPermission(authorityId);
            if (dataArray != null && !dataArray.isEmpty()) {
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                AccessOilPojo accessOil = new AccessOilPojo();
                accessOil.setAuthority(new AuthorityPojo());
                accessOil.setOil(new OilPojo());
                accessOil.setOptUser(Long.valueOf(loginUser.getUserId()));
                for (String oilId : dataArray) {
                    accessOil.getOil().setOilId(Long.valueOf(oilId));
                    accessOil.getAuthority().setAuthorityId(authorityId);
                    accessOil.setAccessOilId(idWorker.nextId());
                    accessOilService.insertAccessOil(accessOil);
                }
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            } else {
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            }
        } catch (Exception e) {
            resultModel.setResult(false);
            resultModel.setDetail("更新失败！");
            logger.error(LOG + "：更新失败，信息为：" + e.getMessage());
        }
        return resultModel;
    }

    /**
     * 更新配置车辆权限
     *
     * @param authorityId
     * @param dataArray
     * @param httpSession
     * @return
     */
    @RequestMapping("/updateUserPower")
    @ResponseBody
    public ResultModel updateUserPower(@RequestParam(value = "authorityId") long authorityId, @RequestParam(required = false, value = "dataArray[]") List<String> dataArray, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        try {
            //清空本次操作权限组的所有内容
            authorityUserService.revokeUserPermission(authorityId, 0);
            if (dataArray != null && !dataArray.isEmpty()) {
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                AuthorityUserPojo authorityUser = new AuthorityUserPojo();
                authorityUser.setAuthority(new AuthorityPojo());
                authorityUser.setUser(new UserPojo());
                authorityUser.setOptUser(Long.valueOf(loginUser.getUserId()));
                for (String userId : dataArray) {
                    /*
                    清空本次操作用户的所在权限组
                    (权限组-用户 : 1-n) : 不屏蔽
                    (权限组-用户 : n-n) : 屏蔽
                     */
//                    authorityUserService.revokeUserPermission(0, Long.valueOf(userId));
                    authorityUser.getUser().setUserId(Long.valueOf(userId));
                    authorityUser.getAuthority().setAuthorityId(authorityId);
                    authorityUser.setAuthorityUserId(idWorker.nextId());
                    authorityUserService.insertAuthorityUser(authorityUser);
                }
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            } else {
                resultModel.setResult(true);
                resultModel.setDetail("更新成功！");
            }
        } catch (Exception e) {
            resultModel.setResult(false);
            resultModel.setDetail("更新失败！");
            logger.error(LOG + "：更新失败，信息为：" + e.getMessage());
        }
        return resultModel;
    }
}
