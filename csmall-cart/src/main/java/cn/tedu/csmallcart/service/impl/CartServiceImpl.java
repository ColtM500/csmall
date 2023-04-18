package cn.tedu.csmallcart.service.impl;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.entity.Cart;
import cn.tedu.csmallcart.mapper.CartMapper;
import cn.tedu.csmallcart.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    //执行新增商品信息到购物车的方法
    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        //当前方法参数是cartAddDTO 我们实现新增需要的是Cart
        //类型不同 需要将cartAddDTO 转换为Cart对象
        Cart cart = new Cart();
        //利用BeanUtils将同名属性赋值到cart
        BeanUtils.copyProperties(cartAddDTO,cart);
        //然后执行新增数据的方法
        int num = cartMapper.insertCart(cart);
        //可以通过num值，判断新增是否成功
        log.info("购物车中新增了商品:{}",cart);
    }


    //删除购物车表中商品信息的方法
    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        //直接调用根据用户id和商品编号删除的方法即可
        cartMapper.deleteCartByUserIdAndCommodityCode(userId, commodityCode);
        log.info("购物车商品已删除");
    }
}
