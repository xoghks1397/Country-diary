package com.ssafy.test.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReReply {

	private String replyId;
	private String text;
	private String date;
	
	@Builder
	public ReReply(String replyId, String text, String date) {
		this.replyId = replyId;
		this.text = text;
		this.date = date;
	}
}
