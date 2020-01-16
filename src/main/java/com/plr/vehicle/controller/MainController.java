package com.plr.vehicle.controller;

import com.plr.vehicle.model.CountModel;
import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.AuthorityPojo;
import com.plr.vehicle.pojo.VehiclePojo;
import com.plr.vehicle.pojo.OilPojo;
import com.plr.vehicle.pojo.UserPojo;
import com.plr.vehicle.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/MainController")
public class MainController {
    @Resource
    private IUserService userService;
    @Resource
    private IAuthorityService authorityService;
    @Resource
    private IVehicleService deviceService;
    @Resource
    private IOilService thingService;
    @Resource
    private IManufacturerService manufacturerService;
    @Resource
    private IDriverService driverService;

    @RequestMapping("/getCount")
    @ResponseBody
    public CountModel getCount(HttpSession httpSession) {
        CountModel countModel = new CountModel();
        UserModel loginUser = (UserModel) httpSession.getAttribute(httpSession.getId());
        List<String> authorityIds = authorityService.getAuthorityIdsByUserId(Long.valueOf(loginUser.getUserId()));
        countModel.setUser(userService.getUserCount(new UserPojo()));
        countModel.setAuthority(authorityService.getAuthorityCount(new AuthorityPojo()));
        countModel.setDriver(driverService.getEnableDriverCount());
        countModel.setVehicle(deviceService.getAccessVehicleCount(new VehiclePojo(), authorityIds));
        countModel.setOil(thingService.getAccessOilCount(new OilPojo(), authorityIds));
        countModel.setMfr(manufacturerService.getManufacturerCount());
        return countModel;
    }
}
