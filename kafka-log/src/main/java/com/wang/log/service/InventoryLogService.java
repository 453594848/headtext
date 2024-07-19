package com.wang.log.service;


import brave.Tracer;
import cn.hutool.core.util.IdUtil;
import com.wang.log.mapper.InventoryLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class InventoryLogService {
    private final InventoryLogMapper inventoryLogMapper;
    private final Tracer tracer;

    public InventoryLogService(InventoryLogMapper inventoryLogMapper, Tracer tracer) {
        this.inventoryLogMapper = inventoryLogMapper;
        this.tracer = tracer;
    }

    @KafkaListener(topics = "inventory-log", groupId = "inventory-log-group")
    public void listen(String logMessage) {
        long traceId = tracer.currentSpan().context().traceId();
        String traceIdString = Long.toUnsignedString(traceId);
        LocalDateTime time = LocalDateTime.now();
        inventoryLogMapper.insertLog(logMessage, time, traceIdString);
        log.info("kafka日志记录：{}", logMessage);
    }


    public void insertLog(String logMessage) {
        LocalDateTime time = LocalDateTime.now();
        Long traceId = tracer.currentSpan().context().traceId();
        String uuid = IdUtil.randomUUID();
        inventoryLogMapper.insertLog(logMessage, time, uuid);
        log.info("feign日志记录：{}", logMessage);
    }
}
