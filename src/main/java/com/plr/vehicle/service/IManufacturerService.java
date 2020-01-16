package com.plr.vehicle.service;


import com.plr.vehicle.model.ManufacturerModel;
import com.plr.vehicle.pojo.ManufacturerPojo;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:25
 */
public interface IManufacturerService {

    /**
     * 获取所有的厂商数量
     * @return
     */
    int getManufacturerCount();

    /**
     * 分页查询厂商
     * @param page
     * @param limit
     * @return
     */
    List<ManufacturerModel> listManufacturer(int page, int limit);

    /**
     *
     * @param mfrName
     * @param page
     * @param limit
     * @return
     */
    List<ManufacturerModel> listManufacturerByMfrName(String mfrName, int page, int limit);

    /**
     *
     * @param mfrCode
     * @param page
     * @param limit
     * @return
     */
    List<ManufacturerModel> listManufacturerByMfrCode(String mfrCode, int page, int limit);

    /**
     * 获取所有厂商ID和厂商名称
     * @return
     */
    List<Map<String, String>> listMfrIdAndMfrName();

    /**
     * 批量删除厂商
     * @param listMfrId
     * @return
     */
    int deleteManufacturer(List<Long> listMfrId);

    /**
     * 通过ID删除厂商
     * @param mfrId
     * @return
     */
    int deleteManufacturerById(Long mfrId);

    /**
     *
     * @param manufacturerPojo
     * @return
     */
    int insertManufacturer(ManufacturerPojo manufacturerPojo);

    /**
     *
     * @param manufacturerPojo
     * @return
     */
    int updateManufacturer(ManufacturerPojo manufacturerPojo);

    /**
     * 根据厂商名模糊查新时，获取厂商数量
     * @param mfrName
     * @return
     */
    int getMfrCountByMfrName(String mfrName);

    /**
     * 根据厂商编码模糊查新时，获取厂商数量
     * @param mfrCode
     * @return
     */
    int getMfrCountByMfrCode(String mfrCode);
}
