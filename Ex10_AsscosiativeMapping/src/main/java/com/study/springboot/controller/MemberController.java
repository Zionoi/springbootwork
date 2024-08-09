package com.study.springboot.controller;

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
	
	
	//의존성주입
	@Autowired
	MemberService memberService;
	
	//루트로
	@RequestMapping("/")
	public String root() throws Exception {
		return "index";
	}
	
	// insert
	@RequestMapping("/minsert")
	public String minsert(Member member, Model model) {
		Member m = memberService.minsert(member);
		model.addAttribute("member",m);
		
		return "minsert";
	}
	
	@RequestMapping("/mupdate")
	public String mupdate(String id, String name, Model model) {
		
		Optional<Member> member = memberService.findById(id);
		
		Member m = member.get();
		m.setName(name);
		
		Member uMember = memberService.minsert(m);
		model.addAttribute("member",uMember);
		
		return "index";
	}
	
	
}
