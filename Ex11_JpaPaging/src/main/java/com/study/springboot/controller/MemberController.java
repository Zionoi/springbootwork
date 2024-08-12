package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() {
		return "menu";
	}
	
	@RequestMapping("/selectByNameLike")
	public String selectByNameLike(String name, int page, Model model) {
		String search = name + "%";
		/*
		    페이징을 할경우
			* Pageable 인터페이스 를 사용
				: Spring에서 Pagination을 지원하는 Pageble인터페이스 제공
				- getPageNumver() : 현재 페이지 번호를 반환(0부터 시작)
				- getPageSize() : 한 페이지당 최대 항목 수를 반환
				- getOffset() : 현재 페이지의 시작 위치를 반환
				- getSort() : 정렬한 정보를 반환
				- next() : 다음페이지 정보를 반환
				- previous() : 이전페이지 정보를 반환
			
			* PageRequest 클래스
				: Spring Date JPA에서 제공하는 Pageable 구현체 중 하나로 페이지 정보를 생성하는 클래스
				- page : 조회할 페이지 번호(0부터 시작)
				- size: 한 페이지당 항목수
				- sort : 정렬 정보(생략가능)
				- direction : 정령 방향(ASC, DESC)
				- properties : 정렬 대상 속성명
				
				> 생성자
				PageRequest(int page, int size)  => ex) 1페이지당 몇개씩 보여줄건지 설정해서 생성자 생성
				PageRequest(int page, int size, Sort sort)
				PageRequest(int page, int size, Sort.Direction direction, String... properties)						
		*/
		Sort sort = Sort.by(Sort.Order.desc("name")); // 필드명으로 넣어야함.
//		PageRequest pr = PageRequest.of(page-1, 10, sort); // 리퀘스트에 담거나 부모격인 Pageable에 담을수 있다
//		Pageable pageable = PageRequest.of(page-1, 10, sort);
		/*PageRequest pr = PageRequest.ofSize(10)
									.withPage(page-1)
									.withSort(sort);*/ // 이렇게도 가능 하지만 위에처럼 생성자로 만드는게 더 편하다
//		Pageable pageable = PageRequest.of(page-1, 10, Sort.by("name").ascending()); // 이런식으로 생성할때 sort를 만들어줄수도 있다. ascending 오름차순  descending 내림차순
		
		Pageable pageable = PageRequest.of(page-1, 10, sort);
		
		Page<Member> result = memberService.selectByNameLike(search, pageable);
		List<Member> content = result.getContent(); // 실제 객체가 담긴 List<Member>가 반환됨.
		long totalElements = result.getTotalElements(); // 총 레코드 수
		int totalPages = result.getTotalPages(); 	// 총 페이지 수
		int size = result.getSize();				//한 페이지당 항목 수
		int nowPage = result.getNumber()+1;			//현재페이지(0부터 시작)
		int numberOfElements = result.getNumberOfElements();	// 현재 페이지의 항목 수
		
		model.addAttribute("members", content);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("numberOfElements", numberOfElements);
		
		return "selectList";
	}
		
	
	
}
