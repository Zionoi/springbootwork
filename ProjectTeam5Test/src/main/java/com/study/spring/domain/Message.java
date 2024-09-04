package com.study.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	private Long mNum;
	
	@ManyToOne
    @JoinColumn(name = "memId") 
    private Member member;
	
	private String mContent;
	private String createSysdate;
}
