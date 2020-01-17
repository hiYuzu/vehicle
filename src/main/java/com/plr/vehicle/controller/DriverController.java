package com.plr.vehicle.controller;

import com.plr.vehicle.model.DriverModel;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.pojo.DriverPojo;
import com.plr.vehicle.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping("/DriverController")
public class DriverController extends BaseController {


    @Autowired
    private IDriverService driverService;

    @RequestMapping(value = "/getDriver", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultListModel<DriverModel> getDriver(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("driverName") String driverName, @RequestParam("isUsable") int isUsable) {
        ResultListModel<DriverModel> result = ResultListModel.getInstance();
        result.addDataList(driverService.listDriver(page, limit, driverName, isUsable));
        result.setCount(result.getData().size());
        return result;
    }


    @RequestMapping("/delDriver")
    @ResponseBody
    public ResultModel delDriver(@RequestParam("driverId") String driverId) {
        int result = driverService.deleteDriverById(Long.valueOf(driverId));
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "删除成功！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "删除失败！");
    }

    @RequestMapping("/addDriver")
    @ResponseBody
    public ResultModel insertDriver(DriverPojo pojo, @RequestParam("isUsable") int isUsable) {
        initOperator(pojo);
        pojo.setUsable(isUsable);
        int result = driverService.insertDriver(pojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "添加成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "驾驶员存在！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "新增驾驶员失败！");
    }

    @RequestMapping(value = "/updateDriver", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultModel updateDriver(DriverPojo pojo, @RequestParam("isUsable") int isUsable) {
        initOperator(pojo);
        pojo.setUsable(isUsable);
        int result = driverService.updateDriver(pojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "更新驾驶员成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "驾驶员重复！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "更新失败！");
    }


}
