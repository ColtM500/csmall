package cn.tedu.csmall.stock.service;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;


public interface IStockService {

    void reduceStock(StockReduceCountDTO stockReduceCountDTO);
}
