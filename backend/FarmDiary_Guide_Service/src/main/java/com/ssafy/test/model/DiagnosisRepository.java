package com.ssafy.test.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface DiagnosisRepository extends MongoRepository<Diagnosis, String> {
	public Diagnosis findByCropName(String cropName);
	public Diagnosis findByFileName(String cropName);
}
