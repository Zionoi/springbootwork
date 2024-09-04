package com.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectTeam5TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTeam5TestApplication.class, args);
	}

}
