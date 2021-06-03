package com.ssafy.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FarmDiaryGuideServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmDiaryGuideServiceApplication.class, args);
	}

}
