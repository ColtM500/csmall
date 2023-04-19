package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.stock.service.IStockService;
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
    public JsonResult reduceStockCount(StockReduceCountDTO stockReduceCountDTO){
         service.reduceStock(stockReduceCountDTO);
         return JsonResult.ok("减少商品库存数量完成!");
    }
}
