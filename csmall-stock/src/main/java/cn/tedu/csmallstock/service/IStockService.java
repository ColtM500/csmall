package cn.tedu.csmallstock.service;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import org.springframework.stereotype.Repository;


public interface IStockService {

    void reduceStock(StockReduceCountDTO stockReduceCountDTO);
}
