package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServieImpl implements IBusinessService {

    //Dubbo调用Order模块实现新增订单的功能
    //在消费者装配其他模块提供的业务逻辑层对象时，建议在对象名称前添加Dubbo
    @DubboReference
    private IOrderService dubboOrderService;

    @Override
    public void buy() {
        //编写新增订单业务雏形
        //先实例化一个要新增的订单对象
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PU201");
        orderAddDTO.setCount(10);
        orderAddDTO.setMoney(200);
        //这个实例化好的DTO对象 后期会发送到order模块 新增订单
        log.info("新增订单信息为:{}",orderAddDTO);
        //发送给order模块执行相关的数据库操作
        dubboOrderService.orderAdd(orderAddDTO);
    }
}
