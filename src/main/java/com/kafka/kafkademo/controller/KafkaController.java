package com.kafka.kafkademo.controller;

import com.kafka.kafkademo.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {


    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http:localhost:8081/api/v1/kafka/publish?message=Hello kafka
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Send message successfully to Kafka Broker");
    }
}
