package com.mapstructdemo.mapstructdemo;

import com.mapstructdemo.mapstructdemo.beans.dto.CarDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.DriverDTO;
import com.mapstructdemo.mapstructdemo.beans.dto.PartDTO;
import com.mapstructdemo.mapstructdemo.beans.vo.CarVO;
import com.mapstructdemo.mapstructdemo.convert.CarConvert;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapstructDemoApplication.class)
public class MapstructDemoApplicationTests {

    @Test
    public void test1() {
        // 模拟业务构造出的carDTO对象
        CarDTO carDTO = buildCarDTO();
        //转化dto->vo
        CarVO carVO = CarConvert.INSTANCE.abc(carDTO);
        System.out.println(carVO);
//        carVO.setId(carDTO.getId());
        //......

//        double totalPrice = carDTO.getTotalPrice();
//        DecimalFormat df = new DecimalFormat("#.00");
//        df.format(totalPrice);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        sdf.format(date);
    }


    private CarDTO buildCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(330L);
        carDTO.setVin("vin12313221");
        carDTO.setPrice(132.312d);
        carDTO.setTotalPrice(2131212.222);
        carDTO.setPublishDate(new Date());
        carDTO.setBrand("大众");
        //零件
        PartDTO partDTO1 = new PartDTO();
        partDTO1.setPartId(1L);
        partDTO1.setPartName("多功能方向盘");
        PartDTO partDTO2 = new PartDTO();
        partDTO2.setPartId(2L);
        partDTO2.setPartName("智能车门");
        List<PartDTO> partDTOList = new ArrayList<>();
        partDTOList.add(partDTO1);
        partDTOList.add(partDTO2);
        carDTO.setPartDTOS(partDTOList);
        //设置驾驶员...
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(88L);
        driverDTO.setName("小明");
        carDTO.setDriverDTO(driverDTO);
        //https://www.bilibili.com/video/BV1E5411n7HR/?p=2&spm_id_from=pageDriver&vd_source=6b520ccf1d3b6e0c04809fd09e6efc9f
        return carDTO;
    }

}
