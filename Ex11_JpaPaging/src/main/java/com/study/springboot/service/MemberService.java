package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	
	//Page로 반환받음
	public Page<Member> selectByNameLike(String search, Pageable pageable) {
		return memberRepository.findByNameLike(search, pageable);
		
	}

	
}
