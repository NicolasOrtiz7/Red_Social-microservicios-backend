package com.socialnetwork.profile.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    /**
    * Configuración para el PRODUCER de Kafka.
    * Configuración necesaria para crear un KafkaTemplate
        que se utilizará para enviar mensajes a un TOPIC
     */

    private final String bootstrapServers = "localhost:9092";

    /** Crea un map de propiedades que configuran el PRODUCER */
    public Map<String, Object> producerConfig(){

        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Puerto localhost:9092
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // key serializer
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // value serializer

        return properties;
    }

    /** Crea una instancia de ProducerFactory utilizando la configuración del producerConfig() */
    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig()); // patrón de diseño Factory
    }

    /** Crea un KafkaTemplate que se utilizará para enviar mensajes a un topic */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

}
