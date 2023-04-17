package cn.tedu.csmall.commons.pojo.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "新增订单的DTO")
@Data
public class OrderAddDTO implements Serializable {
    @ApiModelProperty(value = "用户id",name="userId",example = "UU100")
    private String userId;
    @ApiModelProperty(value = "商品编号",name="commodityCode",example = "PC100")
    private String commodityCode;
    @ApiModelProperty(value = "购买数量",name="count",example = "5")
    private Integer count;
    @ApiModelProperty(value = "总金额",name="money",example = "500")
    private Integer money;

}
