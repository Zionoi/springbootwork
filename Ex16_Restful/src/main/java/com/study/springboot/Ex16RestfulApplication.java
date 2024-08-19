package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Bean클래스에서 @AuditingEntityListener 어노테이션 사용했다면 해당 어노테이션을 메인에 꼭 달아줘야함 (시간 자동 기록관리해주는 어노테이션)
@SpringBootApplication
public class Ex16RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex16RestfulApplication.class, args);
	}

}
