package com.kafka.service;


import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.model.Professor;
import com.kafka.model.Student;

@Service
@KafkaListener(topics = "test-events")
public class KafkaConsumerService {
	
	@KafkaHandler
	public void student(Student stu) {
		System.out.println(stu);
	}
	
	@KafkaHandler
	public void professor(Professor pro) {
		System.out.println(pro);
	}
	@KafkaHandler(isDefault = true)
	public void defaultMethod(Object obj) {
		System.out.println(obj);
	}


}
