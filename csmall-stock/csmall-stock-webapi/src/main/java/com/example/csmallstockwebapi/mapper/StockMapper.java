package com.example.csmallstockwebapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {

    //减少库存的方法
    @Update("update stock_tbl set count=count-#{reduceCount} " +
            " where commodity_code=#{commodityCode} and count>=#{reduceCount}")
    int updateReduceStockCount(
            @Param("commodityCode") String commodityCode,
            @Param("reduceCount") Integer reduceCount);
}
