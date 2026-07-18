package com.kafka.kafkademo.controller;

import com.kafka.kafkademo.dto.User;
import com.kafka.kafkademo.kafka.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/json/kafka")
public class KafkaJsonController {

    private final JsonKafkaProducer jsonKafkaProducer;

    public KafkaJsonController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    // http://localhost:8081/api/v1/json/kafka/publish
    @PostMapping("/publish")
    public ResponseEntity<String> publishJsonMessage(@RequestBody User user) {
        jsonKafkaProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message published to kafka topic");
    }
}
