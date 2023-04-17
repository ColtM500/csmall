package cn.tedu.csmall.commons.pojo.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "新增购物车的DTO")
public class CartAddDTO implements Serializable {

    @ApiModelProperty(value = "商品编号", name = "commodityCode", example = "PC100")
    private String commodityCode;

    @ApiModelProperty(value = "商品价格", name = "price", example = "20")
    private Integer price;

    @ApiModelProperty(value = "商品数量", name = "count", example = "100")
    private Integer count;

    @ApiModelProperty(value = "用户ID", name = "userId", example = "UU100")
    private String userId;

}
