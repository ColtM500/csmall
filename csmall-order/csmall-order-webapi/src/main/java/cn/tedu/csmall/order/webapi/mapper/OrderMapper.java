package cn.tedu.csmall.order.webapi.mapper;

import cn.tedu.csmall.commons.pojo.order.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    //新增订单的方法
    @Insert("insert into order_tbl(user_id,commodity_code,count,money) values" +
             "(#{userId},#{commodityCode},#{count},#{money})")
    int insertOrder(Order order);

    //分页查询所有订单的方法
    //使用PageHelper框架完成分页查询的原理是在sql语句运行时，在sql语句后添加limit关键字
    //所以在持久层编写方法时，没有任何分页查询的特征，也无需关注分页业务（注解和xml都是）
    @Select("select id, user_id, commodity_code, count, money from order_tbl")
    List<Order> findAllOrders();
}
