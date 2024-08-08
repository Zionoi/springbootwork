package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	public Member insert(Member member) {
		
					//insert할땐 save()메소드 사용(JPA에 자체 API(메소드))
		Member rMember = memberRepository.save(member);
		return rMember;
			
	}

}
