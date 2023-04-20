package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import cn.tedu.csmallcartservice.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    //添加@DubboReference注册 表示当前业务逻辑层需要消费其他模块的服务
    //注解下面声明的业务逻辑层接口 必须在nacos中有对应的注册实现类
    //在调用时，Dubbo可以自动获取Nacos中注册的业务逻辑层实现类对象
    @DubboReference
    private IStockService stockService;
    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //1.这里先要进行减少数据库中库存数的操作（调用stock模块）
        //要执行库存的减少需要StockReduceCountDTO对象
        StockReduceCountDTO countDTO = new StockReduceCountDTO();
        //countDTO 不能直接使用同名属性赋值 因为和OrderAddDTO属性不同名
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        //执行Dubbo调用 完成库存减少
        stockService.reduceStock(countDTO);

        //2.还要从购物车中删除用户选中的商品(调用cart模块)
        //执行Dubbo调用 完成购物车删除
        cartService.deleteUserCart(orderAddDTO.getUserId(), orderAddDTO.getCommodityCode());

        // 使用随机数,让业务运行时有几率发生异常
        if(Math.random()>0.5){
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,"故意抛出的异常(不是代码错误)");
        }

        //3.上面两个操作完成后，再进行订单的新增
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        //执行新增
        orderMapper.insertOrder(order);
        log.info("新增订单信息为:{}",order);
    }
}
