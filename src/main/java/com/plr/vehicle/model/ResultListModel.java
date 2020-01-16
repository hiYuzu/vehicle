package com.plr.vehicle.model;
import com.plr.vehicle.util.DefaultParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:50
 */
public class ResultListModel<T> extends ResultModel {

    /**
     * 返回标识
     */
    private int code = DefaultParam.liSelectError;

    /**
     * 返回结果条数
     */
    private int count = 0;

    /**
     * 返回信息
     */
    private String msg = "";

    /**
     * 返回数据
     */
    private List<T> data = new ArrayList<T>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSelect() {
        return msg;
    }

    public void setSelect(String select) {
        this.msg = select;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(T d) {
        data.add(d);
    }

    public void addDataList(List<T> data) {
        this.data.addAll(data);
    }

    public static <T> ResultListModel<T> getInstance(int totalCount) {
        ResultListModel<T> result = new ResultListModel<>();
        result.setCode(DefaultParam.liSelectOk);
        result.setResult(true);
        result.setCount(totalCount);
        return result;
    }

    public static <T> ResultListModel<T> getInstance() {
        ResultListModel<T> result = new ResultListModel<>();
        result.setCode(DefaultParam.liSelectOk);
        result.setResult(true);
        return result;
    }

    public ResultListModel(){}

    public ResultListModel(int count, List<T> data, String msg) {
        this.code = DefaultParam.liSelectOk;
        this.count = count;
        this.data = data;
        this.msg = msg;
        this.setResult(true);
    }

}
