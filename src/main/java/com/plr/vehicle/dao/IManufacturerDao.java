package com.plr.vehicle.dao;

import com.plr.vehicle.pojo.ManufacturerPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 14:12
 */
public interface IManufacturerDao {

    int getManufacturerCount(@Param("mfrCode") String mfrCode, @Param("mfrName") String mfrName);

    ManufacturerPojo getManufacturerByCode(@Param("mfrCode") String mfrCode);

    List<ManufacturerPojo> listManufacturer(@Param("rowIndex") int rowIndex, @Param("rowCount") int rowCount, @Param("mfrCode") String mfrCode, @Param("mfrName") String mfrName);

    List<Map<String, String>> listMfrIdAndMfrName();

    int deleteManufacturerByIdList(@Param("idList") List<Long> idList);

    int deleteManufacturerById(@Param("mfrId") long mfrId);

    int insertManufacturer(@Param("manufacturerPojo") ManufacturerPojo manufacturerPojo);

    int updateManufacturer(@Param("manufacturerPojo") ManufacturerPojo manufacturerPojo);

}
