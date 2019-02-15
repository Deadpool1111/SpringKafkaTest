package com.kafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

	@Value("${kafka.consumer.bootstrap-server}")
	private String bootstrapServer;

	@Bean("consumerConfigs")
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-4");
		props.put(JsonDeserializer.TYPE_MAPPINGS,
				"professor:com.kafka.model.Professor, student:com.kafka.model.Student");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "test-4");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
		return props;
	}

	@Bean("consumerFactory")
	public ConsumerFactory<String, Object> consumerFactory() {
		JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>(Object.class);
		jsonDeserializer.addTrustedPackages("*");
		
 		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), jsonDeserializer);
	}

	@Bean("kafkaListenerContainerFactory")
	@ConditionalOnMissingBean(name = "KafkaAutoConfiguration")
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
