package cn.tedu.csmall.order.webapi.controller;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单管理模块")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    @ApiOperation("新增订单")
    @SentinelResource(value = "新增订单的方法",
                      blockHandler = "blockError",
                      fallback = "fallbackError")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO){
        orderService.orderAdd(orderAddDTO);
        return JsonResult.ok("新增订单完成!");
    }

    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO,
                                 BlockException e){
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR,"服务器繁忙，请稍后重试!"
        );
    }

    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,
                                    Throwable throwable){
        throwable.printStackTrace();
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR,
            "方法运行发生异常，执行了降级方法: " + throwable.getMessage()
        );
    }
}
