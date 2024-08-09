package com.study.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long bno;
	private String title;
	private String content;
	
	@ManyToOne	// 다대일의 관계 (한사람이 여러 게시글을 쓸수있어서 해당 어노테이션 사용)
	@JoinColumn(name="writer")	// board테이블에 writer컬럼을 추가하고 외래키로 지정  
	private Member member;		//외래키로 가져오는 어노테이션 member의 프라이머키()를 가져옴
	
}
