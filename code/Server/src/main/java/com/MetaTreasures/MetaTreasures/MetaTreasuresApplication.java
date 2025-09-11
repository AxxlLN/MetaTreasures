package com.MetaTreasures.MetaTreasures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetaTreasuresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaTreasuresApplication.class, args);
	}

}
