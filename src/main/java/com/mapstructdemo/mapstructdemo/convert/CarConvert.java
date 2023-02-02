package com.mapstructdemo.mapstructdemo.convert;

import com.mapstructdemo.mapstructdemo.beans.dto.CarDTO;
import com.mapstructdemo.mapstructdemo.beans.vo.CarVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zeronly 2023/1/31
 */
@Mapper
public abstract class CarConvert {
    public static CarConvert INSTANCE= Mappers.getMapper(CarConvert.class);
    /**
     * carDTO --> carVO
     * */
    public abstract CarVO abc(CarDTO carDTO);
}
