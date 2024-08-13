package com.study.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

@Controller
@SessionAttributes({"loginUser"}) // loginUser은 리퀘스트 스코프인데 세션으로 바꿔주려면 이 어노테이션을 사용한다.
public class MemberControll {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// root는 resources 파일. thymleaf는 프리픽스 서픽스 설정안해도 resources 파일밑에 index파일 찾아갈수있음
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/enrollForm")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	@GetMapping("/idCheck")
	public @ResponseBody boolean checkId(@RequestParam("id") String id) {
		
		return memberService.idCheck(id);
	}
	
	@PostMapping("/memberInsert")
	public String memberInsert(Member member) {
		String enPass = passwordEncoder.encode(member.getPassword());	// encode() : 넘겨준 비밀번호를 암호화해주는 메소드. 
		member.setPassword(enPass);										// => 암호된 비밀번호를 member 객체 setPassword로 값 넣어주기
		//member.setPassword(passwordEncoder.encode(member.getPassword()));		// 위에 과정을 한줄로
		memberService.memberInsert(member);
		return "redirect:/";
		
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		
		Optional<Member> loginUser = memberService.login(member);
		if(loginUser.isPresent()) {
			Member m = loginUser.get();
			if(passwordEncoder.matches(member.getPassword(), m.getPassword())) { // passwordEncoder.matches(input비번, DB비번) : 입력한 비번과 db비번이 맞는지 비교해주는 메소드
				model.addAttribute("loginUser",m);
			}
		}
		return "redirect:/";
	}
}
