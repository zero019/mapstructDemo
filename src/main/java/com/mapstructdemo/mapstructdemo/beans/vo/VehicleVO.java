package com.mapstructdemo.mapstructdemo.beans.vo;/**
 * @author zeronly 2023/2/3
 */

import lombok.Data;

/**
 *@author 听风丶
 *Create by 2023/2/3 10:33
 */
@Data
public class VehicleVO {
    //编号
    private Long id;
    //裸车价格
    private Double price;
    //品牌
    private String brand;
}
