package com.ssafy.test.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomRepositoryImpl implements CustomRepository{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void updateReReply(ReReply reReply) {
		Query query = new Query().addCriteria(Criteria.where( "_id" ).is( new ObjectId(reReply.getReplyId())) );
	    Update update = new Update();
	    List<Document> array = new ArrayList<>();
	    
	    Document item = new Document(); 
	    item.put("text", reReply.getText());
	    item.put("date", reReply.getDate());
	    array.add(item);
	    
	    update.push("reReply").each(array);
	    mongoTemplate.updateFirst(query, update, "reply");  
	}
	
	
}
