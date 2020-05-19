package com.plr.vehicle.controller;

import com.plr.vehicle.util.DefaultParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/5/19 9:03
 */
@Controller
@RequestMapping("/Gps")
public class GpsController {
    @RequestMapping("/getGpsInfo")
    @ResponseBody
    public Map<String, Double> getGpsInfo() {
        return "".equals(DefaultParam.point) ? null : getPoint(DefaultParam.point);
    }

    private Map<String, Double> getPoint(String point){
        int position = point.indexOf(",");
        Map<String, Double> result = new HashMap<>(3);
        result.put("pointX", Double.valueOf(point.substring(0, position - 1).trim()));
        result.put("pointY", Double.valueOf(point.substring(position + 1, point.length() - 1).trim()));
        return result;
    }
}
