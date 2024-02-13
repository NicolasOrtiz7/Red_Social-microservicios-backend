package com.socialnetwork.profile.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    /** Esta clase es para CREAR TOPICS */

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configurations = new HashMap<>();
        // DELETE = borra el mensaje, COMPACT = mantiene el más actual
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Tiempo de retención del mensaje. Por defecto = -1 (no se borran)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "8640000");
        // Tamaño máximo del segmento 1GB
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        // Tamaño máximo de cada mensaje. Por defecto = 1MB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");


        return TopicBuilder
                .name("segundotopic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }


}
