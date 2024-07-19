package com.wang.log.service;


import com.wang.log.mapper.InventoryLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class InventoryLogService {
    private final InventoryLogMapper inventoryLogMapper;

    public InventoryLogService(InventoryLogMapper inventoryLogMapper) {
        this.inventoryLogMapper = inventoryLogMapper;
    }

    @KafkaListener(topics = "inventory-log", groupId = "inventory-log-group")
    public void listen(String logMessage) {
        LocalDateTime time = LocalDateTime.now();
        inventoryLogMapper.insertLog(logMessage,time);
        log.info("日志记录：{}", logMessage);
    }


    public void insertLog(String logMessage) {
        LocalDateTime time = LocalDateTime.now();
        inventoryLogMapper.insertLog(logMessage,time);
        log.info("日志记录：{}", logMessage);
    }
}
