package com.plr.vehicle.controller;

import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.OilModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.OilPojo;
import com.plr.vehicle.service.IAuthorityService;
import com.plr.vehicle.service.IOilService;
import com.plr.vehicle.service.IUserService;
import com.plr.vehicle.util.DateUtil;
import com.plr.vehicle.util.IdWorkerUtil;
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
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping(value = "/OilController")
public class OilController {
    /**
     * 日志输出标记及日志服务对象
     */
    private static final String LOG = "OilController";
    private static Logger logger = LoggerFactory.getLogger(OilController.class);
    /**
     * 声明服务
     */
    @Resource
    private IOilService oilService;

    @Resource
    private IAuthorityService authorityService;

    @Resource
    private IUserService userService;

    @Resource
    private IdWorkerUtil idWorker;

    /**
     * 获取油卡列单(配置权限)
     *
     * @param oilModel
     * @param httpSession
     * @return
     */
    @RequestMapping("/getAccessOil")
    @ResponseBody
    public ResultListModel<OilModel> getAccessOil(String authorityId, OilModel oilModel, HttpSession httpSession) {
        OilPojo oil = convertOil(oilModel, httpSession);
        List<String> authorityIdSubs = new ArrayList<>();
        authorityIdSubs.add(authorityId);
        List<OilPojo> oilListSub = oilService.getAccessOil(oil, authorityIdSubs);
        List<OilPojo> oilList = oilService.getAccessOil(oil, null);
        List<OilModel> oilModelList = new ArrayList<>();
        for (OilPojo temp : oilList) {
            temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
            OilModel oilModelTemp = convertOilModel(temp);
            if (oilListSub != null) {
                if (oilListSub.contains(temp)) {
                    oilModelTemp.setChecked(true);
                }
            }
            if (oilModelTemp != null) {
                oilModelList.add(oilModelTemp);
            }
        }
        return new ResultListModel<>(oilModelList.size(), oilModelList, null);
    }

    /**
     * 获取油卡列单(视图)
     *
     * @param oilModel
     * @param httpSession
     * @return
     */
    @RequestMapping("/getAuthorityOil")
    @ResponseBody
    public ResultListModel<OilModel> getAuthorityOil(OilModel oilModel, HttpSession httpSession) {
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        List<String> authorityIds = authorityService.getAuthorityIdsByUserId(Long.valueOf(loginUser.getUserId()));
        OilPojo oil = convertOil(oilModel, httpSession);
        int count = oilService.getAccessOilCount(oil, authorityIds);
        if (count > 0) {
            List<OilPojo> oilList = oilService.getAccessOil(oil, authorityIds);
            List<OilModel> oilModelList = new ArrayList<>();
            for (OilPojo temp : oilList) {
                temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
                OilModel oilModelTemp = convertOilModel(temp);
                if (oilModelTemp != null) {
                    oilModelList.add(oilModelTemp);
                }
            }
            return new ResultListModel<>(count, oilModelList, "成功查询油卡");
        } else {
            return ResultListModel.getInstance(count);
        }
    }

    /**
     * 删除油卡
     *
     * @param list
     * @return
     */
    @RequestMapping("/deleteOil")
    @ResponseBody
    public ResultModel deleteOil(@RequestParam(value = "list[]") List<String> list) {
        ResultModel resultModel = new ResultModel();
        if (list != null && list.size() > 0) {
            try {
                int intResult = oilService.deleteOil(list);
                if (intResult > 0) {
                    resultModel.setResult(true);
                    resultModel.setDetail("油卡删除成功！");
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("油卡删除失败！未找到相应油卡，请刷新后重试。");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("油卡删除失败！");
                logger.error(LOG + "：油卡删除失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("油卡删除失败，错误原因：服务器未接收到删除数据！");
        }
        return resultModel;
    }

    /**
     * 更新油卡
     *
     * @param oilModel
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateOil", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel updateOil(OilModel oilModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (oilModel != null) {
            try {
                if (oilService.existUpdateOil(oilModel.getOilId(), oilModel.getOilCode()) == 0) {
                    OilPojo oil = convertOil(oilModel, httpSession);
                    int intResult = oilService.updateOil(oil);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("更新油卡成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("更新油卡失败，信息未改变。");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此油卡编码，请更换！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("油卡更新失败！");
                logger.error(LOG + "：油卡更新失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    @RequestMapping(value = "/insertOil")
    @ResponseBody
    public ResultModel insertOil(OilModel oilModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (oilModel != null) {
            OilPojo oil = convertOil(oilModel, httpSession);
            try {
                String oilCode = oilModel.getOilCode();
                String oilId = String.valueOf(idWorker.nextId());
                if (oilService.existUpdateOil(oilId, oilCode) == 0) {
                    oil.setOilId(Long.valueOf(oilId));
                    int intResult = oilService.insertOil(oil);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("油卡添加成功!");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("油卡添加失败！");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此油卡编码，请使用其他编码！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("油卡添加失败！");
                logger.error(LOG + "：油卡添加失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    private OilPojo convertOil(OilModel oilModel, HttpSession httpSession) {
        OilPojo oil = new OilPojo();
        if (oilModel != null) {
            try {
                if (oilModel.getOilId() != null && !oilModel.getOilId().isEmpty()) {
                    oil.setOilId(Long.valueOf(oilModel.getOilId()));
                }
                oil.setOilCode(oilModel.getOilCode());
                oil.setOilManufacturer(oilModel.getOilManufacturer());
                if (oilModel.getOilBalance() != null && !oilModel.getOilBalance().isEmpty()){
                    oil.setOilBalance(Double.valueOf(oilModel.getOilBalance()));
                }
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                if (loginUser != null && loginUser.getUserId() != null && !loginUser.getUserId().isEmpty()) {
                    oil.setOptUser(Long.valueOf(loginUser.getUserId()));
                }
                if (oilModel.getOptTime() != null && !oilModel.getOptTime().isEmpty()) {
                    oil.setOptTime(DateUtil.StringToTimestamp(oilModel.getOptTime(), DateUtil.DATA_TIME_SECOND));
                } else {
                    oil.setOptTime(DateUtil.GetSystemDateTime(0));
                }
                oil.setRowCount(oilModel.getRowCount());
                oil.setRowIndex(oilModel.getRowIndex());
            } catch (Exception e) {
                logger.error(LOG + "：将OilModel转换成OilPojo失败，信息为" + e.getMessage());
            }
        }
        return oil;
    }


    private OilModel convertOilModel(OilPojo oil) {
        OilModel oilModel = new OilModel();
        if (oil != null) {
            oilModel.setOilId(String.valueOf(oil.getOilId()));
            oilModel.setOilCode(oil.getOilCode());
            oilModel.setOilManufacturer(oil.getOilManufacturer());
            oilModel.setOilBalance(String.valueOf(oil.getOilBalance()));
            oilModel.setOptUser(String.valueOf(oil.getOptUser()));
            oilModel.setOptUserName(oil.getOptUserName());
            oilModel.setOptTime(DateUtil.TimestampToString(oil.getOptTime(), DateUtil.DATA_TIME_SECOND));
        }
        return oilModel;
    }
}
