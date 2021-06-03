package com.ssafy.test.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface GuideRepository extends MongoRepository<Guide, String> {
	public List<Guide> findByPlace(String place);
	public List<Guide> findByName(String name);
}
