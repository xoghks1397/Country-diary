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
@Document(collection = "sharing")
public class Sharing {

	@Id
	private String id;
	private String title;
	private String contents;
	private String category;
	private String date;
	private List<String> img;
	
	@Builder
	public Sharing(int bId, String title, String contents, String category, String date, List<String> img) {
		this.title = title;
		this.contents = contents;
		this.category = category;
		this.date = date;
		this.img = img;
	}
	
}
