package cn.tedu.csmallcart.controller;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmallcart.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/base/cart")
@Api(tags = "购物车管理模块")
public class CartController {

    //装配业务逻辑层接口
    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    @ApiOperation("新增商品信息到购物车")
    public JsonResult cardAdd(CartAddDTO cartAddDTO){
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok("新增商品到购物车完成!");
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID", name = "userId", example = "UU100"),
            @ApiImplicitParam(value = "商品编号", name = "commodityCode", example = "PC100")
    })
    public JsonResult cardDelete(String userId, String commodityCode){
        cartService.deleteUserCart(userId, commodityCode);
        return JsonResult.ok("删除购物车中的商品信息成功!");
    }
}
