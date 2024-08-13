package com.study.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MemberPWEncoder {
	//메소드를 빈으로 관리
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//비밀번호 암호화해주는 crypto메소드(MVN레파지토리 사이트에서 임플리먼트 그래들 주소 가져옴)
	}
}
