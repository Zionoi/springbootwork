package com.study.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	public boolean idCheck(String id) {
		// .existsById() : 아이디가 존재하는지 DB 확인해주는 JPA 자체 메소드. 반환결과 true or false반환
		return memberRepository.existsById(id);	
	}

	public Member memberInsert(Member member) {
		 // void로 리턴 안넘겨도 된다
		return memberRepository.save(member);
	}

	public Optional<Member> login(Member member) {
		return memberRepository.findById(member.getId());
		
	}
}
