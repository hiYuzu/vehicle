package com.plr.vehicle.model;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:23
 */
public class ResultModel {
    public static final boolean SUCCESS = true;
    public static final boolean ERROR = false;


    /**
     * 是否成功标志
     */
    private boolean result = false;

    /**
     * 详细信息
     */
    private String detail = null;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public static ResultModel getInstance(boolean isSuccess,String detail){
        ResultModel resultModel = new ResultModel();
        resultModel.setResult(isSuccess);
        resultModel.setDetail(detail);
        return resultModel;
    }
}
