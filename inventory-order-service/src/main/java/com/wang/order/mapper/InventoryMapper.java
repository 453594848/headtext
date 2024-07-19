package com.wang.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InventoryMapper {

    @Select("select saleable_inventory from demo.product_stock_47009 where sku_code =#{productId}")
    Integer getStock(int productId);

    @Update("update demo.product_stock_47009 set saleable_inventory = saleable_inventory-#{quantity} where sku_code=#{productId}")
    int reduceStock(int productId, int quantity);
}
