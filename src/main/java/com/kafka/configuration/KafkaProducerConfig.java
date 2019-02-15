package com.kafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${kafka.producer.bootstrap-server}")
	private String bootstrapServer;
	
	@Bean
	public ProducerFactory<String, Object> getProducerFactory(){
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG,"easytest");
		properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
		properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "./ssl/easy-test-stg.target.com.jks");
		properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "targetAZaz09$$");
		properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "./ssl/client.truststore.jks");
		properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "targetAZaz09$$");
		properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "targetAZaz09$$");
		properties.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
		
		return new DefaultKafkaProducerFactory<>(properties);
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(getProducerFactory());
	}
	
	
	
}
