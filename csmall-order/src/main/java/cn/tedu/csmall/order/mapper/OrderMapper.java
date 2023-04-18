package cn.tedu.csmall.order.mapper;

import cn.tedu.csmall.commons.pojo.order.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    //新增订单的方法
    @Insert("insert into order_tbl(user_id,commodity_code,count,money) values" +
             "(#{userId},#{commodityCode},#{count},#{money})")
    int insertOrder(Order order);


}
