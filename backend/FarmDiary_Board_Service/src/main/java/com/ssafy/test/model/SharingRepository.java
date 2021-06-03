package com.ssafy.test.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SharingRepository extends MongoRepository<Sharing, String>, CustomRepository{

}
