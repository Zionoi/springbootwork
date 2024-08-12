package com.study.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.domain.Member;

@Repository					//JpaRepository<bean클래스, bean클래스안에 프라이머리키 변수 자료형 (@Id가 붙은 필드의 자료형)>
public interface MemberRepository extends JpaRepository<Member, Long>{
	//findBy 뒤에 컬럼을 붙여주면 컬럼에 해당되는것을 select해준다
	

	Page<Member> findByNameLike(String search, Pageable pageable);

	
	
	
}
