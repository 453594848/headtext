package com.wang.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kafka-log-service")
public interface InventoryLogClient {
    @PostMapping("/logs")
    void sendLog(@RequestBody String logMessage);
}
