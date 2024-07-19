package com.wang.log.controller;

import com.wang.log.service.InventoryLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping
public class InventoryLogController {
    @Resource
    private InventoryLogService inventoryLogService;

    @PostMapping("/logs")
    public void saveLog(@RequestBody String logMessage) {
        inventoryLogService.insertLog(logMessage);
        log.info("feign远程调用同步日志记录：{}", logMessage);
    }
}
