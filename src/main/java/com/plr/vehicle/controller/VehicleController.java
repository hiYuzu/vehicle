package com.plr.vehicle.controller;

import com.plr.vehicle.model.*;
import com.plr.vehicle.pojo.VehiclePojo;
import com.plr.vehicle.pojo.VehicleTypePojo;
import com.plr.vehicle.pojo.ManufacturerPojo;
import com.plr.vehicle.service.*;
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
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping(value = "/VehicleController")
public class VehicleController {
    /**
     * 日志
     */
    private static final String LOG = "VehicleController";
    private Logger logger = LoggerFactory.getLogger(VehicleController.class);
    /**
     * 服务
     */
    @Resource
    private IVehicleService vehicleService;

    @Resource
    private IdWorkerUtil idWorker;

    @Resource
    private IUserService userService;

    @Resource
    private IAuthorityService authorityService;

    @Resource
    private IVehicleTypeService vehicleTypeService;

    @Resource
    private IManufacturerService manufacturerService;

    @RequestMapping("/getAccessVehicle")
    @ResponseBody
    public ResultListModel<VehicleModel> getAccessVehicle(String authorityId, VehicleModel vehicleModel, HttpSession httpSession) {
        VehiclePojo vehicle = convertVehicle(vehicleModel, httpSession);
        List<String> authorityIdSubs = new ArrayList<>();
        authorityIdSubs.add(authorityId);
        List<VehiclePojo> vehicleListSub = vehicleService.getAccessVehicle(vehicle, authorityIdSubs);
        List<VehiclePojo> vehicleList = vehicleService.getAccessVehicle(vehicle, null);
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        for (VehiclePojo temp : vehicleList) {
            temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
            VehicleModel vehicleModelTemp = convertVehicleModel(temp);
            if (vehicleListSub != null) {
                if (vehicleListSub.contains(temp)) {
                    vehicleModelTemp.setChecked(true);
                }
            }
            if (vehicleModelTemp != null) {
                vehicleModelList.add(vehicleModelTemp);
            }
        }
        return new ResultListModel<>(vehicleModelList.size(), vehicleModelList, null);
    }

    @RequestMapping("/getAuthorityVehicle")
    @ResponseBody
    public ResultListModel<VehicleModel> getAuthorityVehicle(VehicleModel vehicleModel, HttpSession httpSession) {
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        List<String> authorityIds = authorityService.getAuthorityIdsByUserId(Long.valueOf(loginUser.getUserId()));
        VehiclePojo vehicle = convertVehicle(vehicleModel, httpSession);
        int count = vehicleService.getAccessVehicleCount(vehicle, authorityIds);
        if (count > 0) {
            List<VehiclePojo> vehicleList = vehicleService.getAccessVehicle(vehicle, authorityIds);
            List<VehicleModel> vehicleModelList = new ArrayList<>();
            for (VehiclePojo temp : vehicleList) {
                temp.setOptUserName(userService.getUserNameById(temp.getOptUser()));
                VehicleModel vehicleModelTemp = convertVehicleModel(temp);
                if (vehicleModelTemp != null) {
                    vehicleModelList.add(vehicleModelTemp);
                }
            }
            return new ResultListModel<>(vehicleModelList.size(), vehicleModelList, null);
        } else {
            return ResultListModel.getInstance(count);
        }
    }

    /**
     * 获取车辆类型，所属厂商，监测机构信息列表
     *
     * @return
     */
    @RequestMapping("/getForeignInfo")
    @ResponseBody
    public List<Map<String, String>> getForeignInfo() {
        List<Map<String, String>> mapList1 = vehicleTypeService.getTypeIdAndTypeName();
        List<Map<String, String>> mapList2 = manufacturerService.listMfrIdAndMfrName();
        mapList1.addAll(mapList2);
        return mapList1;
    }

    /**
     * 删除车辆
     *
     * @param list
     * @return
     */
    @RequestMapping("/deleteVehicle")
    @ResponseBody
    public ResultModel deleteVehicle(@RequestParam(value = "list[]") List<String> list) {
        ResultModel resultModel = new ResultModel();
        if (list != null && list.size() > 0) {
            try {
                int intResult = vehicleService.deleteVehicle(list);
                if (intResult > 0) {
                    resultModel.setResult(true);
                    resultModel.setDetail("删除成功！");
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("删除车辆失败！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("删除车辆失败！");
                logger.error(LOG + "：删除车辆失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("删除车辆失败，错误原因：服务器未接收到删除数据！");
        }
        return resultModel;
    }

    /**
     * 更新车辆
     *
     * @param vehicleModel
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateVehicle", method = {RequestMethod.POST})
    @ResponseBody
    public ResultModel updateVehicle(VehicleModel vehicleModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (vehicleModel != null) {
            VehiclePojo vehicle = convertVehicle(vehicleModel, httpSession);
            try {
                if (vehicleService.existUpdateVehicle(vehicle.getVehicleId(), vehicle.getVehicleCode()) == 0) {
                    int intResult = vehicleService.updateVehicle(vehicle);
                    if (intResult > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("车辆更新成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("更新失败！");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此车辆编号，请更换！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("车辆更新失败！");
                logger.error(LOG + "：车辆更新失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    @RequestMapping(value = "/insertVehicle")
    @ResponseBody
    public ResultModel insertVehicle(VehicleModel vehicleModel, HttpSession httpSession) {
        ResultModel resultModel = new ResultModel();
        if (vehicleModel != null) {
            VehiclePojo vehicle = convertVehicle(vehicleModel, httpSession);
            vehicle.setVehicleId(idWorker.nextId());
            try {
                if (vehicleService.existUpdateVehicle(vehicle.getVehicleId(), vehicle.getVehicleCode()) == 0) {
                    if (vehicleService.insertVehicle(vehicle) > 0) {
                        resultModel.setResult(true);
                        resultModel.setDetail("车辆添加成功！");
                    } else {
                        resultModel.setResult(false);
                        resultModel.setDetail("车辆添加失败！");
                    }
                } else {
                    resultModel.setResult(false);
                    resultModel.setDetail("系统已存在此车辆编号，请使用其他编号！");
                }
            } catch (Exception e) {
                resultModel.setResult(false);
                resultModel.setDetail("车辆添加失败！");
                logger.error(LOG + "：车辆添加失败，信息为：" + e.getMessage());
            }
        } else {
            resultModel.setResult(false);
            resultModel.setDetail("没有可以操作的数据！");
        }
        return resultModel;
    }

    @RequestMapping(value = "/getOilRecord")
    @ResponseBody
    public ResultListModel<OilRecordModel> getOilRecord(String timeRange, String vehicleCode){
        List<OilRecordModel> recordModels = vehicleService.getOilRecord(timeRange, vehicleCode);
        return new ResultListModel<>(recordModels.size(), recordModels, "success");
    }

    @RequestMapping(value = "/getRepairRecord")
    @ResponseBodyil
    public ResultListModel<RepairRecordModel> getRepairRecord(String timeRange, String vehicleCode){
        List<RepairRecordModel> recordModels = vehicleService.getRepairRecord(timeRange, vehicleCode);
        return new ResultListModel<>(recordModels.size(), recordModels, "success");
    }

    private VehiclePojo convertVehicle(VehicleModel vehicleModel, HttpSession httpSession) {
        VehiclePojo vehicle = new VehiclePojo();
        if (vehicleModel != null) {
            try {
                if (vehicleModel.getVehicleId() != null && !vehicleModel.getVehicleId().isEmpty()) {
                    vehicle.setVehicleId(Long.valueOf(vehicleModel.getVehicleId()));
                }
                vehicle.setVehicleCode(vehicleModel.getVehicleCode());
                vehicle.setVehicleName(vehicleModel.getVehicleName());
                vehicle.setVehicleBrand(vehicleModel.getVehicleBrand());
                vehicle.setUsable(vehicleModel.getIsUsable() == 1);
                if (vehicleModel.getVehiclePrice() != null && !vehicleModel.getVehiclePrice().isEmpty()) {
                    vehicle.setVehiclePrice(Integer.parseInt(vehicleModel.getVehiclePrice()));
                }
                UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
                if (loginUser != null && loginUser.getUserId() != null && !loginUser.getUserId().isEmpty()) {
                    vehicle.setOptUser(Long.valueOf(loginUser.getUserId()));
                }
                if (vehicleModel.getOptTime() != null && !vehicleModel.getOptTime().isEmpty()) {
                    vehicle.setOptTime(DateUtil.StringToTimestamp(vehicleModel.getOptTime(), DateUtil.DATA_TIME_SECOND));
                } else {
                    vehicle.setOptTime(DateUtil.GetSystemDateTime(0));
                }
                vehicle.setRowCount(vehicleModel.getRowCount());
                vehicle.setRowIndex(vehicleModel.getRowIndex());
                //车辆类型外键
                vehicle.setVehicleType(new VehicleTypePojo());
                if (vehicleModel.getVtId() != null && !vehicleModel.getVtId().isEmpty()) {
                    vehicle.getVehicleType().setVtId(Long.valueOf(vehicleModel.getVtId()));
                }
                vehicle.getVehicleType().setVtCode(vehicleModel.getVtCode());
                vehicle.getVehicleType().setVtName(vehicleModel.getVtName());
                if (vehicleModel.getVtType() != null && !vehicleModel.getVtType().isEmpty()) {
                    vehicle.getVehicleType().setVtType(Long.valueOf(vehicleModel.getVtType()));
                }
                //所属厂商外键
                vehicle.setManufacturer(new ManufacturerPojo());
                if (vehicleModel.getMfrId() != null && !vehicleModel.getMfrId().isEmpty()) {
                    vehicle.getManufacturer().setMfrId(Long.valueOf(vehicleModel.getMfrId()));
                }
                vehicle.getManufacturer().setMfrCode(vehicleModel.getMfrCode());
                vehicle.getManufacturer().setMfrAddress(vehicleModel.getMfrAddress());
                vehicle.getManufacturer().setMfrName(vehicleModel.getMfrName());
                vehicle.getManufacturer().setMfrRemark(vehicleModel.getMfrRemark());
            } catch (Exception e) {
                logger.error(LOG + "：将VehicleModel转换成VehiclePojo失败，信息为" + e.getMessage());
            }
        }
        return vehicle;
    }

    private VehicleModel convertVehicleModel(VehiclePojo vehicle) {
        VehicleModel vehicleModel = new VehicleModel();
        if (vehicle != null) {
            vehicleModel.setVehicleId(String.valueOf(vehicle.getVehicleId()));
            vehicleModel.setVehicleCode(vehicle.getVehicleCode());
            vehicleModel.setVehicleName(vehicle.getVehicleName());
            vehicleModel.setVehicleBrand(vehicle.getVehicleBrand());
            if (vehicle.isUsable()) {
                vehicleModel.setIsUsable(1);
                vehicleModel.setIsUsableString("可用");
            } else {
                vehicleModel.setIsUsable(0);
                vehicleModel.setIsUsableString("不可用");
            }
            vehicleModel.setVehiclePrice(String.valueOf(vehicle.getVehiclePrice()));
            vehicleModel.setOptUser(String.valueOf(vehicle.getOptUser()));
            vehicleModel.setOptUserName(vehicle.getOptUserName());
            vehicleModel.setOptTime(DateUtil.TimestampToString(vehicle.getOptTime(), DateUtil.DATA_TIME_SECOND));
            //车辆类型外键
            if (vehicle.getVehicleType() != null) {
                vehicleModel.setVtId(String.valueOf(vehicle.getVehicleType().getVtId()));
                vehicleModel.setVtCode(vehicle.getVehicleType().getVtCode());
                vehicleModel.setVtName(vehicle.getVehicleType().getVtName());
                vehicleModel.setVtType(String.valueOf(vehicle.getVehicleType().getVtType()));
            }
            //所属厂商外键
            if (vehicle.getManufacturer() != null) {
                vehicleModel.setMfrId(String.valueOf(vehicle.getManufacturer().getMfrId()));
                vehicleModel.setMfrCode(vehicle.getManufacturer().getMfrCode());
                vehicleModel.setMfrName(vehicle.getManufacturer().getMfrName());
                vehicleModel.setMfrAddress(vehicle.getManufacturer().getMfrAddress());
                vehicleModel.setMfrRemark(vehicle.getManufacturer().getMfrRemark());
            }
        }
        return vehicleModel;
    }
}
