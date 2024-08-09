package com.study.springboot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/insert")									//요청처리후 값 반환하기위한 model객체 사용
	public String insert(@RequestParam("username") String username, Model model) {
		Member member = Member.builder()
							  .username(username)
							  .createDate(LocalDate.now())
							  .build();
		Member result = memberService.insert(member);
		model.addAttribute("member",result);
		return "insert";
	}
	
	@RequestMapping("/select")
	public String select(@RequestParam("id") Long id, Model model) {
		Optional<Member> member = memberService.select(id);
		//.isPresent() : 안에 데이터가 있는가? true false반환
		if(member.isPresent()) {
			model.addAttribute("member", member.get());		//Optional의 wrapping을 풀어서 Member객체로 내보낼땐 .get()을 사용
		}else {
			model.addAttribute("member", null);
		}
		return "select";
	}
	
	@RequestMapping("/selectAll")
	public String selectAll(Model model) {
		List<Member> list = memberService.selectAll();
		model.addAttribute("mList", list);
		return "selectAll";
	}
	
	/*	@RequestMapping("/update")
		public String update(Member m, Model model) {
			m.setCreateDate(LocalDate.now());
			Member member = memberService.update(m);
			model.addAttribute("member",member);
			
			return "update";
		}*/
	
	@RequestMapping("/delete")
	public String delete(Long id, Model model) {
		memberService.delete(id);
		return "menu";
	}
	
	
	
	
}
