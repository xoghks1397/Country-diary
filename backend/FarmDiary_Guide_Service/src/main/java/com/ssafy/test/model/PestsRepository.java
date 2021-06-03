package com.ssafy.test.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PestsRepository extends MongoRepository<Pests, String> {
	public List<Pests> findByCropName(String cropName);
	public List<Pests> findByPestNameAndCropName(String pestName, String cropName);
}
