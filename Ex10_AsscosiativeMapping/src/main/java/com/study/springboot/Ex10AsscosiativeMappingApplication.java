package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@CreatedDate와 @LastModifiedDate를 사용할 때 반드시 아래 어노테이션추가
@EnableJpaAuditing		// DB데이터가 수정및 입력될때 해당시간을 관리해주는 어노테이션
@SpringBootApplication
public class Ex10AsscosiativeMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex10AsscosiativeMappingApplication.class, args);
	}

}
