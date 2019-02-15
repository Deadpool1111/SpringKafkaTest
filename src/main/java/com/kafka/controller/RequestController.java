package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.service.KafkaProducerService;

@RestController
@RequestMapping(value="/kafka")
public class RequestController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@GetMapping(value="/producer")
	public void producerRequest() {
		kafkaProducerService.produce();
	}

}
