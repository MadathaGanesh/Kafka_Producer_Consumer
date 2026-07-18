package com.kafka.kafkademo.kafka;

import com.kafka.kafkademo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * JsonKafkaProducer: Service class is responsible for publishing "User" object to kafka topic.
 * This producer uses Spring Kafka's {kafkaTemplate} to serialize the {User object} to JSON and sends it to kafka topic.
 *
 * <p>Topic: {@code jsonTopic}</p>
 */
@Service
public class JsonKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaProducer.class);
    /***
     * Spring kafka template used to send messages with:
     *  Key : String,
     *  value : User
     */
    private final KafkaTemplate<String, User> kafkaTemplate;


    // Injecting values externally from Application.properties file. No need to hardcode value
    @Value("${spring.kafka.json-topic.name}")
    private String jsonTopicName;


    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a {User} object as a JSON message to the kafka topic.
     * <p>
     * This message performs the following steps:
     * 1) Logs the outgoing message
     * 2) Creates a Spring {Message} with the {User} object as payload.
     * 3) Sets the target kafka topic using {KafkaHeaders}
     * 4) sends the message asynchronously using {kafkaTemplate}
     *
     * @param data {User} object to be published to topic
     */
    public void sendJsonMessage(User data) {
        logger.info("Sending Json Message to Kafka : {}", data.toString());

        // Build a Spring Message containing the payload and target kafka topic.
        Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, jsonTopicName)   // Here we are using second topic named, "jsonTopic" for "Json serialization" and "JSON Deserialization".
                .build();

        // Send the message asynchronously to kafka
        kafkaTemplate.send(message)
                .whenComplete((res, ex) -> {
                    if (ex == null) {
                        logger.info("Successfully send Json Message to Kafka : {}", res.toString());
                        logger.info("Topic name : {}", res.getRecordMetadata().topic());
                        logger.info("Offset : {}", res.getRecordMetadata().offset());
                    } else {
                        logger.error("Error while sending Json Message to Kafka : {}", ex.getMessage());
                    }
                });


    }
}
