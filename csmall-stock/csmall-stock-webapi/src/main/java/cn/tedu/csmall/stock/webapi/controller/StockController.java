package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
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
    //blockHandler属性是设置在当前方法被限流时，运行的自定义限流方法名称
    //fallback属性是设置在当前方法运行发生异常时，运行的自定义降级方法名称
    //fallback指定的降级方法运行优先级比全局异常处理类高
    @SentinelResource(value = "减少商品库存数量",
                      blockHandler = "blockError",
                      fallback = "fallbackError")
    public JsonResult reduceStockCount(StockReduceCountDTO stockReduceCountDTO){
         service.reduceStock(stockReduceCountDTO);
//        try {
//            Thread.sleep(5000);//5s
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return JsonResult.ok("减少商品库存数量完成!");
    }

    //Sentinel自定义限流方法规则
    //1.访问修饰符必须为public
    //2.返回值类型必须和被限流的控制方法一致
    //3.方法名称必须和@SentinelResource注解中blockHandler设置的名称一致
    //4.参数列表也是和控制器方法一致,在末尾额外添加一个BlockException类型的参数
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO,
                                 BlockException e){
        //这个方法会在请求被限流时执行，返回值应包含相应的提示信息
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR, "服务器忙，请稍后再试！"
        );
    }

    //自定义降级方法的格式和上面限流方法基本一致
    //区别是参数列表末尾添加的异常类型Throwable
    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,
                                    Throwable throwable){
        //先输出异常的信息
        throwable.printStackTrace();
        //返回JsonResult对象
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "方法运行发生异常，执行了降级方法"+throwable.getMessage());
    }
}
