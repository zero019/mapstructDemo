package com.mapstructdemo.mapstructdemo.beans.vo;

import lombok.Data;

/**
 * @author zeronly 2023/1/31
 */
@Data
public class PartVO {
    /**汽车零件的id*/
    private Long partId;
    /**零件的名字，比如多功能方向盘*/
    private String partName;
}
