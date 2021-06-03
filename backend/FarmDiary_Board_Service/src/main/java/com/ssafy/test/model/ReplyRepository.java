package com.ssafy.test.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReplyRepository extends MongoRepository<Reply, String>, CustomRepository {

	List<Reply> findByBoardId(String boardId);
}
