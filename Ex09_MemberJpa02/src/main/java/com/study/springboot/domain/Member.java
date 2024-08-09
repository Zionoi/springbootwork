package com.study.springboot.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter					// lombok 게터 생성
@Setter					// lombok 세터 생성
@AllArgsConstructor		// lombok 모든필드생성자 생성
@NoArgsConstructor		// lombok 기본 생성자 생성
@Builder				// lombok 빌더 생성
@Entity(name="member02")
public class Member {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String email;
	
	public Member(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}
	
	
}
