package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.domain.Member;

@Repository					//JpaRepository<bean클래스, bean클래스안에 프라이머리키 변수 자료형 (@Id가 붙은 필드의 자료형)>
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	
	
	
}
