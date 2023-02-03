package com.mapstructdemo.mapstructdemo.beans.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zeronly 2023/1/31
 */
@Data
public class CarDTO {
    //编号
    private Long id;
    //车辆编号
    private String vin;
    //裸车价格
    private double price;
    //上路的价格
    private double totalPrice;
    //车的颜色,不想将color映射给vo
    private String color;
    //生产日期
    private Date publishDate;
    //品牌名字
    private String brand;
    //汽车包含的零件列表
    private List<PartDTO> partDTOS;
    //汽车的司机
    private DriverDTO driverDTO;
}
