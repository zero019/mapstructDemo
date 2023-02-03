package com.mapstructdemo.mapstructdemo.convert;

import com.mapstructdemo.mapstructdemo.beans.dto.CarDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.DriverDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.PartDTO;
import com.mapstructdemo.mapstructdemo.beans.vo.CarVO;
import com.mapstructdemo.mapstructdemo.beans.vo.DriverVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zeronly 2023/1/31
 */
@Mapper
public abstract class CarConvert {
    public static CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);

    /**
     * carDTO --> carVO
     */
    @Mappings(
            value = {
                    @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.00"),
                    @Mapping(source = "publishDate",target = "publishDate",dateFormat = "yyyy-MM-dd HH:mm:ss"),
                    @Mapping(target = "color",ignore = true),
                    @Mapping(source = "brand",target = "brandName"),
                    @Mapping(source = "driverDTO",target = "driverVO")
            }

    )
    // target就是映射到转化成的目标对象这里是CarVO
    // source在这里就是CarDTO内部的字段
    public abstract CarVO abc(CarDTO carDTO);

    /**
     * driverDTO --> driverVO
     * @param driverDTO
     * @return
     */
    @Mappings(
            value = {
                    @Mapping(source = "id",target = "driverId"),
                    @Mapping(source = "name",target = "fullName")
            }
    )
    public abstract DriverVO driverDTO2driverVO(DriverDTO driverDTO);

    @AfterMapping
    /**
     * mapstruct调用完自动转换的方法后，自动调用本方法
     */
    public void dto2voAfter(CarDTO carDTO, @MappingTarget CarVO carVO){
        /**
         * @MappingTarget表示传来的driverVO是已经赋值过的
         */
        List<PartDTO> partDTOList = carDTO.getPartDTOS();
        boolean hasPart = partDTOList != null && !partDTOList.isEmpty();
        carVO.setHasPart(hasPart);
    }

    /**
     * 集合，批量处理
     */
    public abstract List<CarVO> dtos2vos(List<CarDTO> carDTOList);
}
