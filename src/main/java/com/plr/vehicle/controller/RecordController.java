package com.plr.vehicle.controller;

import com.plr.vehicle.model.OilModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.model.VehicleModel;
import com.plr.vehicle.pojo.OilPojo;
import com.plr.vehicle.pojo.VehiclePojo;
import com.plr.vehicle.service.IDriverService;
import com.plr.vehicle.service.IOilService;
import com.plr.vehicle.service.IRecordService;
import com.plr.vehicle.service.IVehicleService;
import com.plr.vehicle.util.IdWorkerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 10:34
 */
@Controller
@RequestMapping("/RecordController")
public class RecordController {
    @Resource
    private IDriverService driverService;
    @Resource
    private IOilService oilService;
    @Resource
    private IVehicleService vehicleService;
    @Resource
    private IRecordService recordService;

    @RequestMapping("/getInfo")
    @ResponseBody
    public Map<String, Object> getInfo() {
        Map<String, Object> resultMap = new HashMap<>(5);
        resultMap.put("driver", driverService.listDriver(0, 0, null, 1));
        resultMap.put("oil", oilService.getAccessOil(new OilPojo(), null));
        resultMap.put("vehicle", vehicleService.getAccessVehicle(new VehiclePojo(), null));
        return longToString(resultMap);
    }

    private Map<String, Object> longToString(Map<String, Object> map) {
        List<OilPojo> oilPojos = (List<OilPojo>) map.get("oil");
        List<VehiclePojo> vehiclePojos = (List<VehiclePojo>) map.get("vehicle");
        List<OilModel> oilModels = new ArrayList<>();
        List<VehicleModel> vehicleModels = new ArrayList<>();
        for (OilPojo oil : oilPojos) {
            OilController oilController = new OilController();
            oilModels.add(oilController.convertOilModel(oil));
        }
        for (VehiclePojo vehicle : vehiclePojos) {
            VehicleController vehicleController = new VehicleController();
            vehicleModels.add(vehicleController.convertVehicleModel(vehicle));
        }
        map.remove("oil");
        map.remove("vehicle");
        map.put("oil", oilModels);
        map.put("vehicle", vehicleModels);
        return map;
    }

    @RequestMapping("/insertRentRecord")
    @ResponseBody
    public ResultModel insertRentRecord(long driverId, long vehicleId, String beginTime, String endTime, double totalCost, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute(session.getId());
        return recordService.insertRentRecord(driverId, vehicleId, beginTime, endTime, totalCost, Long.valueOf(userModel.getUserId()));
    }

    @RequestMapping("/insertOilRecord")
    @ResponseBody
    public ResultModel insertOilRecord(String oilCode, String vehicleCode, double oilCost, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute(session.getId());
        return recordService.insertOilRecord(oilCode, vehicleCode, oilCost, Long.valueOf(userModel.getUserId()));
    }

    @RequestMapping("/insertRepairRecord")
    @ResponseBody
    public ResultModel insertRepairRecord(String vehicleCode, String beginTime, String endTime, double repairCost, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute(session.getId());
        return recordService.insertRepairRecord(vehicleCode, beginTime, endTime, repairCost, Long.valueOf(userModel.getUserId()));
    }
}
