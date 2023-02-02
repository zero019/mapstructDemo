package com.mapstructdemo.mapstructdemo.beans.vo;

import com.mapstructdemo.mapstructdemo.beans.dto.DriverDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.PartDTO;
import lombok.Data;

import java.sql.Driver;
import java.util.Date;
import java.util.List;

/**
 * @author zeronly 2023/1/31
 */
@Data
public class CarVO {
    //编号
    private Long id;
    //车辆编号
    private String vin;
    //裸车价格
    private double price;
    //上路的价格
    private double totalPrice;
    //生产日期
    private Date publishDate;
    //品牌名字
    private String brandName;
    //是否配置了零件
    private Boolean hasPart;
    //汽车的司机
    private DriverVO DriverVO;
}
