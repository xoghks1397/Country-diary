package com.ssafy.test.model;


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
@Document(collection = "pests")

public class Pests {
	@Id
	private String id;
	private String type;
	private String pestName;
	private String cropName;
	private String environment;
	private String descript;
	private String prevent;
	

}
