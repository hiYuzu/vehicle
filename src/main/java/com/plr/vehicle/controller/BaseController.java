package com.plr.vehicle.controller;

import com.plr.vehicle.model.UserModel;
import com.plr.vehicle.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:55
 */
public class BaseController {

    @Autowired
    private HttpSession httpSession;

    protected void initOperator(BasePojo pojo) {
        if(pojo!=null) {
            UserModel operator = (UserModel) httpSession.getAttribute(httpSession.getId());
            pojo.setOptUser(Long.valueOf(operator.getUserId()));
            pojo.setOptUserName(operator.getOptUserName());
            pojo.setOptTime(Timestamp.from(Instant.now()));
        }
    }

}
