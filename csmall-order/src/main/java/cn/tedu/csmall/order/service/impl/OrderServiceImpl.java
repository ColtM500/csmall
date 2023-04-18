package cn.tedu.csmall.order.service.impl;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.csmall.order.mapper.OrderMapper;
import cn.tedu.csmall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //1.这里先要进行减少数据库中库存数的操作（调用stock模块）
        //2.还要从购物车中删除用户选中的商品(调用cart模块)

        //3.上面两个操作完成后，再进行订单的新增
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        //执行新增
        orderMapper.insertOrder(order);
        log.info("新增订单信息为:{}",order);
    }
}
