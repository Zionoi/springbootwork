package com.study.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	@RequestMapping("/selectAll")
	public String selectAll(Model m) {
		List<Member> member = memberService.selectAll(); 
		m.addAttribute("mList",member); 
		m.addAttribute("title", "All");
		return "selectAll";
	}
	
	@RequestMapping("/selectById")
	public String selectById(@RequestParam("id") Long id, Model model) {
		Optional<Member> member = memberService.select(id);
		
		if(member.isPresent()) {
			model.addAttribute("member", member.get());		//Optional의 wrapping을 풀어서 Member객체로 내보낼땐 .get()을 사용
		}else {
			model.addAttribute("member", null);
		}
		model.addAttribute("title", "Id");
		return "select_one";
	}
	
	@RequestMapping("/selectByName")
	public String selectByName(String name, Model model) {
		Optional<Member> member = memberService.selectByName(name);
		model.addAttribute("member", member.get());	// 오류가 날일이 없으면 if없이 바로 model에 넣어도됨.
		model.addAttribute("title", "Name");
		return "select_one";
	}
	
	@RequestMapping("/selectByEmail")
	public String selectByEmail(String email, Model model) {
		Optional<Member> member = memberService.selectByEmail(email);
		model.addAttribute("member", member.get());	
		model.addAttribute("title", "Email");
		return "select_one";
	}
	
	@RequestMapping("/selectByNameLike")
	public String selectByNameLike(String name, Model model) {
		String username = "%"+name +"%";
		List<Member> member = memberService.selectByNameLike(username);
		model.addAttribute("mList", member);
		model.addAttribute("title", "NameLike");
		return "selectAll";
	}
	
	@RequestMapping("/selectByNameLikeDesc")
	public String selectByNameLikeDesc(String name, Model model) {
		String username = "%"+name +"%";
		List<Member> member = memberService.selectByNameLikeDesc(username);
		model.addAttribute("mList", member);
		model.addAttribute("title", "selectByNameLikeDesc");
		return "selectAll";
	}
	
	@RequestMapping("/selectByNameLikeSort")
	public String selectByNameLikeSort(String name, Model model) {
		String username = "%"+name +"%";
		Sort sort = Sort.by(Sort.Order.desc("username"), Sort.Order.asc("email"));
		
		List<Member> member = memberService.selectByNameLikeSort(username, sort);
		model.addAttribute("mList", member);
		model.addAttribute("title", "selectByNameLikeSort");
		return "selectAll";
	}
	
	
	
	
}
