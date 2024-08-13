package com.study.springboot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // : 시간을 자동으로 기록해주는 리스너 db에 알아서 넣어줌 이 어노테이션을 달았다면 main클래스에 꼭 어노테이션을 달아줘야함
public class Member {
	@Id			// 프라이머리키 지정
	private String id;
	@NonNull	// null값 방지
	private String password;
	@NonNull
	private String name;
	private String email;
	private LocalDate birthday;
	private String gender;
	private String phone;
	private String address;
	@CreatedDate	// 자동으로 날짜 설정해주는 어노테이션
	@Column(name="create_date")		// ,컬럼명 지정
	private LocalDateTime createDate;
	
	/*	@Column(name="create_date", insertable=false, updatable=false, columnDefinition="DATE DEFAULT SYSDATE")		// ,컬럼명 지정 / insertable=false : 인설트시 값 넣지 못하게하는 문구 // updatable=false : 업데이트시 값 넣지마시오 // columnDefinition="DATE DEFAULT SYSDATE : 디폴트값 지정
		private LocalDateTime createDate;
	*/	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
}
