package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	public Member insert(Member member) {
		
					//insert할땐 save()메소드 사용(JPA에 자체 API(메소드))
					// 동일한 키가 있으면 update, 키가 없으면 insert 
		Member rMember = memberRepository.save(member);
		return rMember;
			
	}

	/*
		Optional<T> : NullpinterException 발생을 방지하기 위해 사용
					  기존의 반환 값에 타입T에 Optinal<T> Wrapping하여, Null대신 Optional안에 빈 타입 객체를 돌려주는 기법
				ex) Member member = memberRepository.findById(id)
					member.getId();		=> NullpointerException 발생
	*/
	public Optional<Member> select(Long id) {
		// id(프라임머리키(@Id))를 받아서 select할땐 findById()메소드 사용
		// Optional<Member> 래핑 반환값이 없을때 비어있는 빈 타입 객체 반환. 오류를 방지하기 위함
		Optional<Member> member = memberRepository.findById(id);
		return member;		
	}

	public List<Member> selectAll() {
		return memberRepository.findAll(); // 전체 조회할땐 .findAll() 사용
	}
	
	public Member update(Member m) {
		
		Member rMember = memberRepository.save(m);
		return rMember; // 전체 조회할땐 .findAll() 사용
	}

	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	public Optional<Member> selectByName(String name) {
		Optional<Member> member = memberRepository.findByUsername(name);
		return member;
	}

	public Optional<Member> selectByEmail(String email) {
		Optional<Member> member = memberRepository.findByEmail(email);
		return member;
	}

	public List<Member> selectByNameLike(String username) {

		return  memberRepository.findByUsernameLike(username);
	}

	public List<Member> selectByNameLikeDesc(String username) {
		return  memberRepository.findByUsernameLikeOrderByUsernameDesc(username);
	}

	public List<Member> selectByNameLikeSort(String username, Sort sort) {
		return  memberRepository.findByUsernameLike(username, sort);
	}

	
}
