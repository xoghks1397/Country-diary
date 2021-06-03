package com.ssafy.test.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Document(collection = "reply")
public class Reply {

	@Id
	private String id;
	private String boardId;
	private String text;
	private String date;
	private List<ReReply> reReply;
	
	@Builder
	public Reply(String boardId, String text, String date, List<ReReply> reReply) {
		this.boardId = boardId;
		this.text = text;
		this.date = date;
		this.reReply = reReply;
		//this.parentId = parentId;
	}
	
	
	
}
