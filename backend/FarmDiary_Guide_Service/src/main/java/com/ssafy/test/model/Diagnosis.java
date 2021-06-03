package com.ssafy.test.model;


import java.sql.Blob;

import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Document(collection = "diagnosis")

public class Diagnosis {
	@Id
	private String id;
	private String fileName;
	private String cropName;
	private String img;
	private String pestName;
	private String percentage;
	
	public Diagnosis(String fileName, String cropName, String img, String pestName, String percentage) {
		super();
		this.fileName = fileName;
		this.cropName = cropName;
		this.img = img;
		this.pestName = pestName;
		this.percentage = percentage;
	}
	
}
