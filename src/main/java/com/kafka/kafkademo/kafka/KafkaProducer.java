package com.kafka.kafkademo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class is responsible for producing (or) publishing messages to Kafka topic.
 * <p>
 * This class uses the "KafkaTemplate" to send messages to the configured kafka topic
 *
 */

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    // Spring "KafkaTemplate" is used to publish messages to "Kafka topic".
    private KafkaTemplate<String, String> kafkaTemplate;

    // Constructor based dependency Injection
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Sends the message to the kafka topic named "newTopic".
    public void sendMessage(String message) {
        logger.info("send message to topic : message = {}", message);

        // Publish the message to the topic "newTopic".
        kafkaTemplate.send(topicName, message);
    }
}
