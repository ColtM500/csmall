package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServieImpl implements IBusinessService {
    @Override
    public void buy() {
        //编写新增订单业务雏形
        //先实例化一个要新增的订单对象
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(5);
        orderAddDTO.setMoney(100);
        //这个实例化好的DTO对象 后期会发送到order模块 新增订单
        //现在只是业务雏形，输出一下即可
        log.info("新增订单信息为:{}",orderAddDTO);
    }
}
