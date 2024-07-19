package com.wang.order.pojo;

import lombok.Data;

@Data
public class Inventory {
    private Long skuCode;
    private String skuName;
    private Long inventory;
}
