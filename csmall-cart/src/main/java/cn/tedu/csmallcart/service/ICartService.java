package cn.tedu.csmallcart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import org.springframework.stereotype.Repository;


public interface ICartService {

    //新增购物车商品的业务逻辑层
    void cartAdd(CartAddDTO cartAddDTO);

    //删除购物车商品的业务逻辑层
    void deleteUserCart(String userId, String commodityCode);
}
