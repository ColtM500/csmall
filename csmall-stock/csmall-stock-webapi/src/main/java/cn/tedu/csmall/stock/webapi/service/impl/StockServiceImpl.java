package cn.tedu.csmall.stock.webapi.service.impl;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
import cn.tedu.csmall.stock.webapi.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@DubboService注解，标记业务逻辑层实现类，其中所有方法都会注册到Nacos
//其他消费者启动服务时，就可以调用这个类中的任何方法
@DubboService
@Service
@Slf4j
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void reduceStock(StockReduceCountDTO stockReduceCountDTO) {
        int rows = stockMapper.updateReduceStockCount(stockReduceCountDTO.getCommodityCode(),
                                stockReduceCountDTO.getReduceCount());
        if (rows==0){
            //如果上面减少库存数的数据库操作没有影响数据库中的数据
            //就证明这次修改没有成功减少库存 要抛出异常
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"库存不足或商品编号不正确");
        }
        log.info("商品库存减少完成!");
    }
}
