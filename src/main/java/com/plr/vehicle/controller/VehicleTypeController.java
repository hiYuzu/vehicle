package com.plr.vehicle.controller;

import com.plr.vehicle.model.VehicleTypeModel;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.pojo.VehicleTypePojo;
import com.plr.vehicle.service.IVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping("/VehicleTypeController")
public class VehicleTypeController extends BaseController {


    private static final String SEARCH_TYPE_BY_NAME = "typeName";
    private static final String SEARCH_TYPE_BY_CODE = "typeCode";

    @Autowired
    private IVehicleTypeService vehicleTypeService;

    @RequestMapping("/getTypeIdAndTypeName")
    @ResponseBody
    public List<Map<String,String>> getTypeIdAndTypeName() {
        return vehicleTypeService.getTypeIdAndTypeName();
    }

    @RequestMapping(value = "/getVehicleType", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultListModel<VehicleTypeModel> getVehicleType(@RequestParam("searchType") String searchType,
                                                            @RequestParam("searchValue") String searchValue,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("limit") int limit) {
        ResultListModel<VehicleTypeModel> result = ResultListModel.getInstance();
        if (SEARCH_TYPE_BY_NAME.equals(searchType)) {
            result.setCount(vehicleTypeService.getCountByTName(searchValue));
            result.addDataList(vehicleTypeService.listVehicleType(page, limit, null, searchValue));
        } else if (SEARCH_TYPE_BY_CODE.equals(searchType)) {
            result.setCount(vehicleTypeService.getCountByTCode(searchValue));
            result.addDataList(vehicleTypeService.listVehicleType(page, limit, searchValue, null));
        } else {
            result.setCount(vehicleTypeService.getVehicleTypeCount());
            result.addDataList(vehicleTypeService.listVehicleType(page, limit, null, null));
        }
        return result;
    }


    @RequestMapping("/delVehicleType")
    @ResponseBody
    public ResultModel delVehicleType(@RequestParam("vehicleTypeId") String vehicleTypeId) {
        int result = vehicleTypeService.deleteVehicleTypeById(Long.valueOf(vehicleTypeId));
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "删除成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "删除失败，检查是否存在上级设备类型！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "删除失败，检查用户是否登录！");
    }

    @RequestMapping("/addVehicleType")
    @ResponseBody
    public ResultModel insertVehicleType(VehicleTypePojo pojo) {
        initOperator(pojo);
        int result = vehicleTypeService.insertVehicleType(pojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "添加成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "新增设备类型存在！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "新增设备类型失败！");
    }

    @RequestMapping(value = "/updateVehicleType", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultModel updateVehicleType(VehicleTypePojo pojo) {
        initOperator(pojo);
        int result = vehicleTypeService.updateVehicleType(pojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "更新设备类型成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "更新设备类型编码重复！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "更新失败！");
    }


}
