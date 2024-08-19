package com.study.springboot.domain;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name="board2")
@EntityListeners(AuditingEntityListener.class)
public class Board {
	@Id
	@SequenceGenerator( // 시퀀스 설정
			name = "boardseq",
			sequenceName = "board2_seq",
			allocationSize = 1  // 증가값 1씩 증가. 지정안하면 50씩 증가함
			)
	@GeneratedValue(generator="boardseq") // 시퀀스 생성 (시퀀스명 지정)
	private Long no;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;
	@Column(insertable=false, columnDefinition="NUMBER DEFAULT 0")		// insertable=false : 인설트시 값 넣지 못하게하는 문구 // updatable=false : 업데이트시 값 넣지마시오 // columnDefinition="DATE DEFAULT SYSDATE : 디폴트값 지정
	private Long count;
	
	@CreatedDate	// 자동으로 날짜 설정해주는 어노테이션
	@Column(name="create_date")		// ,컬럼명 지정
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	
	
}
