package com.plr.vehicle.controller;

import com.plr.vehicle.model.ManufacturerModel;
import com.plr.vehicle.model.ResultListModel;
import com.plr.vehicle.model.ResultModel;
import com.plr.vehicle.pojo.ManufacturerPojo;
import com.plr.vehicle.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
@Controller
@RequestMapping("/ManufacturerController")
public class ManufacturerController extends BaseController {

    private static final String SEARCH_TYPE_MFR_NAME = "mfrName";
    private static final String SEARCH_TYPE_MFR_CODE = "mfrCode";

    @Autowired
    private IManufacturerService mfrService;

    @RequestMapping(value = "/getManufacturer", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultListModel<ManufacturerModel> getManufacturer(@RequestParam("searchType") String searchType,
                                                              @RequestParam("searchValue") String searchValue,
                                                              @RequestParam("page") int page,
                                                              @RequestParam("limit") int limit) {
        ResultListModel<ManufacturerModel> result = ResultListModel.getInstance();
        if (SEARCH_TYPE_MFR_NAME.equals(searchType)) {
            result.setCount(mfrService.getMfrCountByMfrName(searchValue));
            result.addDataList(mfrService.listManufacturerByMfrName(searchValue, page, limit));
        } else if (SEARCH_TYPE_MFR_CODE.equals(searchType)) {
            result.setCount(mfrService.getMfrCountByMfrCode(searchValue));
            result.addDataList(mfrService.listManufacturerByMfrCode(searchValue, page, limit));
        } else {
            result.setCount(mfrService.getManufacturerCount());
            List<ManufacturerModel> list = mfrService.listManufacturer(page, limit);
            result.setData(list);
        }
        return result;
    }

    @RequestMapping(value = "/delManufacturer", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultModel delManufacturer(String delMfrId) {
        int result = mfrService.deleteManufacturerById(Long.valueOf(delMfrId));
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "删除成功！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "删除失败，检查用户是否登录！");
    }

    @RequestMapping(value = "/addManufacturer", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultModel addManufacturer(ManufacturerPojo manufacturerPojo) {
        initOperator(manufacturerPojo);
        int result = mfrService.insertManufacturer(manufacturerPojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "添加成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "新增厂商存在！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "新增厂商失败！");
    }

    @RequestMapping(value = "/updateManufacturer", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultModel updateManufacturer(ManufacturerPojo manufacturerPojo) {
        initOperator(manufacturerPojo);
        int result = mfrService.updateManufacturer(manufacturerPojo);
        if (result > 0) {
            return ResultModel.getInstance(ResultModel.SUCCESS, "更新厂商成功！");
        } else if (result == -1) {
            return ResultModel.getInstance(ResultModel.ERROR, "更新厂商编码重复！");
        }
        return ResultModel.getInstance(ResultModel.ERROR, "更新失败！");
    }

}
