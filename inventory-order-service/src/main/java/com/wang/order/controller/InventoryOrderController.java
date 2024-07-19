package com.wang.order.controller;


import com.wang.order.result.Result;
import com.wang.order.service.InventoryOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping()
public class InventoryOrderController {

    @Resource
    private InventoryOrderService inventoryOrderService;
    private static final Logger logger = LoggerFactory.getLogger(InventoryOrderController.class);

    @PostMapping("/inventory")
    public Result inventory(@RequestParam Integer id, @RequestParam Long quantity) {
        Result<Object> result = inventoryOrderService.reduceStock(id, quantity);
        System.out.println(MDC.get("LOG_ID"));
        return Result.success(result);
    }


}
