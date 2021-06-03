package com.ssafy.test.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BoardRepository extends MongoRepository<Board, String>, CustomRepository {
	
}
