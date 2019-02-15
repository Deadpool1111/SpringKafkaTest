package com.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.model.Department;
import com.kafka.model.Professor;
import com.kafka.model.Student;

@Service
public class KafkaProducerService {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void produce() {
		Student student = new Student("thomas", "109", 35);
		Department dept = new Department("store pricing", 109);
		Professor professor = new Professor("Ajay", dept);
		//kafkaTemplate.send("stores-pricing-easytest-manual-events", "student", student);
		kafkaTemplate.send("stores-pricing-easytest-manual-events","professor",professor);
	}

}
