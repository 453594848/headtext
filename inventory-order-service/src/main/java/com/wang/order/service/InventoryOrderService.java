package com.wang.order.service;

import com.wang.feign.clients.InventoryLogClient;
import com.wang.order.mapper.InventoryMapper;
import com.wang.order.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@Service
public class InventoryOrderService {
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private InventoryLogClient inventoryLogClient;

    public Result<Object> reduceStock(Integer productId, Long quantity) {

        RLock lock = redissonClient.getLock("inventoryLock:" + productId);
        lock.lock();
        try {
            Integer stock = inventoryMapper.getStock(productId);
            if (stock != null && stock >= quantity) {
                int updatedRows = inventoryMapper.reduceStock(productId, Math.toIntExact(quantity));
                if (updatedRows > 0) {
                    String logMessage=LocalDateTime.now().toString() + productId + " stock reduced by " + quantity;
                     // 同步调用日志服务
                    inventoryLogClient.sendLog(logMessage);
                    // 异步发送日志消息到 Kafka 主题
                    kafkaTemplate.send("inventory-log",logMessage);
                    log.info("库存扣减成功，商品ID：{} ，购买数量： {}，库存数量：{}", productId, quantity, stock - quantity);
                    return Result.success("库存扣减成功");
                } else {
                    log.info("库存不足，商品ID：{}", productId);
                    return Result.error("库存不足");
                }
            } else {
                log.info("用户提交商品ID:{}订单超过库存", productId);
            }
        } finally {
            lock.unlock();
        }
        return Result.error("库存不足");
    }
}

