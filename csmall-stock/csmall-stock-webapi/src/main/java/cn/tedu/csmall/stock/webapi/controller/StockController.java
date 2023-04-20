package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.stock.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "商品库存管理模块")
@RequestMapping("/base/stock")
public class StockController {

    @Autowired
    private IStockService service;


    @PostMapping("/reduce/count")
    @ApiOperation("更新商品库存数量")
    //@SentinelResource是添加在控制器方法上的，在这个方法运行一次后
    //会在Sentinel仪表台中显示，显示后可以设置这个方法的限流策略
    //如果这个方法不运行，就不会在仪表台中显示，也无法设置限流
    //“减少库存数的方法” 这个配置会在仪表台中代表这个方法显示出来
    @SentinelResource("更新商品库存数量")
    public JsonResult reduceStockCount(StockReduceCountDTO stockReduceCountDTO){
         service.reduceStock(stockReduceCountDTO);
         return JsonResult.ok("减少商品库存数量完成!");
    }
}
