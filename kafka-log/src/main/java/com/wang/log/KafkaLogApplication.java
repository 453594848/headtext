package com.wang.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaLogApplication.class, args);
    }
}
