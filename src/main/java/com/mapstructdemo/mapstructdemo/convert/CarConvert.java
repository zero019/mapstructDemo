package com.mapstructdemo.mapstructdemo.convert;

import com.mapstructdemo.mapstructdemo.beans.dto.CarDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.DriverDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.PartDTO;
import com.mapstructdemo.mapstructdemo.beans.vo.CarVO;
import com.mapstructdemo.mapstructdemo.beans.vo.DriverVO;
import com.mapstructdemo.mapstructdemo.beans.vo.VehicleVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zeronly 2023/1/31
 */
@Mapper(componentModel = "spring")
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

    /**
     * 如果VehicleVO和CarDTO之中只需要几个属性，而两者的总属性有太多，用ignore显得冗余
     * 忽略mapstruct的映射行为，只映射配置了mapping的属性
     * @param carDTO
     * @return
     */
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id",target = "id")
    @Mapping(source = "price",target = "price",numberFormat = "#.00")
    public abstract VehicleVO carDTO2VehicleVO(CarDTO carDTO);

    /**
     * 继承配置，在这里是CarDTO转换为VehicleVO，自动继承carDTO2VehicleVO
     * 如存在多个CarDTO转换为VehicleVO方法需要指定方法名
     * @param carDTO
     * @param vehicleVO
     * @return
     */
    @InheritConfiguration(name = "carDTO2VehicleVO")
    public abstract VehicleVO updateVechicleVO(CarDTO carDTO,@MappingTarget VehicleVO vehicleVO);


    /**
     * 会发现carDTO2VehicleVO方法的配置没有被继承，品牌字段被写入carDTO
     * 反向映射不用反过来再写一遍,==注意：只继承@Mapping注解配置，不会继承@BeanMapping==
     * numberFormat失效问题需要注意
     * @param vehicleVO
     * @return
     */
    @InheritInverseConfiguration(name = "carDTO2VehicleVO")
    @Mapping(source = "price",target = "price",numberFormat = "#.00")
    public abstract CarDTO vehicleVO2CarDTO(VehicleVO vehicleVO);
}
