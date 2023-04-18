package cn.tedu.csmallstock.service.impl;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmallstock.mapper.StockMapper;
import cn.tedu.csmallstock.service.IReduceStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReduceStockServiceImpl implements IReduceStockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void reduceStock(StockReduceCountDTO stockReduceCountDTO) {
        stockMapper.updateReduceStockCount(stockReduceCountDTO.getCommodityCode(),
                                stockReduceCountDTO.getReduceCount());
        log.info("商品库存减少完成!");
    }
}
