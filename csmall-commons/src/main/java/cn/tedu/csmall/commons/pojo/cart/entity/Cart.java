package cn.tedu.csmall.commons.pojo.cart.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable {

    private Integer id;
    private String commodityCode;
    private Integer price;
    private Integer count;
    private String userId;

}
