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
@Document(collection = "guide")

public class Guide {
	@Id
	private String id;
	private String name;
	private String place;
	private String environment;
	private String preparation;
	private String plant;
	private String manage;
	private String harvest;
	private String nutri;
	
	public Guide(String name, String place, String environment, String preparation, String plant,
			String manage, String harvest, String nutri) {
		this.name = name;
		this.place = place;
		this.environment = environment;
		this.preparation = preparation;
		this.plant = plant;
		this.manage = manage;
		this.harvest = harvest;
		this.nutri = nutri;
	}
		
}
