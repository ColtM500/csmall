package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonPage;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import cn.tedu.csmallcartservice.ICartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //参数page是页码，pageSize是每页条数
    public JsonPage<Order> getAllOrdersByPage(Integer pageNum, Integer pageSize){
        //PageHelper框架实现分页的方法，就是在执行查询之前，设置分页条件
        //使用PageHelper.startPage方法设置本次查询要查询的页码和每页条数
        //PageHelper的页码从1开始，也就是page是1，就查询第一页
        PageHelper.startPage(pageNum,pageSize);

        //上面的分页条件设置完毕后，下面进行查询，就会在sql语句后自动添加limit关键字
        List<Order> list = orderMapper.findAllOrders();

        //上面的list就是要查询当页数据，但是不包含分页信息（总页数，总条数，有没有上一页等）
        //所以作为分页工具，必须返回包含这个分页信息的对象，也就是声明的返回值PageInfo
        //当前方法返回时，直接实例化PageInfo对象，构造方法中会自动计算分页的信息
        //同时传入list作为参数，将list中的数据赋值给PageInfo
        return JsonPage.restPage(new PageInfo<>(list));
    }
}
