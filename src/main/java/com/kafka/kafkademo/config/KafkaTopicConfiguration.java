package com.kafka.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class used to define kafka-related beans.
 * Spring Boot scans this class during application startup because it is annotated with @Configuration
 */

@Configuration
public class KafkaTopicConfiguration {

    // Injecting values externally from Application.properties file. No need to hardcode values
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.json-topic.name}")
    private String jsonTopicName;

    /**
     * Creates a Kafka topic when the Spring Boot application starts.
     *
     * @Bean tells Spring that the returned object should be
     * registered as a bean in the Spring IoC container.
     * <p>
     * If the topic does not already exist and the Kafka broker
     * allows topic creation, Spring Kafka's KafkaAdmin will
     * automatically create it.
     * <p>
     * Topic Details:
     * - Name       : newTopic
     * - Partitions : 4
     * - Replicas   : 2
     */


    /***
     * Creates a kafka topic when the springBoot application starts/
     *
     * @Bean tells spring that the returned object should be registered as bean in spring IOC container.
     *
     *If the topic does not exist, then the kafka broker allows topic creating. Spring Kafka's kafkaAdmin will automatically create a new topic if it is not existed.
     *
     * @return NewTopic
     *
     * This topic is for general "String" datatype.
     */
    @Bean
    public NewTopic newTopic() {
        return TopicBuilder
                .name(topicName)  // Name of topic
                .partitions(1)  // Number of partitions for parallel processing
                .replicas(1)  // Number of replicas for fault tolerance.
                .build();
    }

    /**
     * This topic is used for "User" class.
     * As we can't convert "JSON object" to "String" directly in above "newTopic". so we are creating a new topic
     * named "jsonTopic", so it helps in converting "User" object to "JSON" and , the "JSON -> User object".
     *
     */
    public NewTopic jsonTopic() {
        return TopicBuilder
                .name(jsonTopicName) // Name of topic
                .build();
    }
}




