package com.wang.order;


import com.wang.feign.clients.InventoryLogClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(clients = InventoryLogClient.class)
@EnableEurekaClient
public class InventoryOrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryOrderApplication.class, args);
    }

}