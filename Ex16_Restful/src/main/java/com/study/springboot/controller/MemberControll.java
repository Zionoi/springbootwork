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
import org.springframework.web.bind.support.SessionStatus;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"loginUser"}) // loginUser은 리퀘스트 스코프인데 세션으로 바꿔주려면 이 어노테이션을 사용한다.
public class MemberControll {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	HttpSession session;
	
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
					model.addAttribute("loginUser",m);	// model은 requestScope임 loginUser를 세션에 담으려면 위쪽에 세션으로 바꿔주는 @SessionAttributes({"loginUser"}) 어노테이션을 사용
				}
			}
			String url = (String)session.getAttribute("boardDetailUrl");
			if(url == null) {
				url = "/";
			}
			return "redirect:"+url;
		}
	
/*	@PostMapping("/login")
	public String login(Member member, Model model) {
	    Optional<Member> loginUser = memberService.login(member);
	    
	    if (loginUser.isPresent()) {
	        Member m = loginUser.get();
	        
	        if (passwordEncoder.matches(member.getPassword(), m.getPassword())) {
	            model.addAttribute("loginUser", m);
	            System.out.println("로그인 성공: " + m.getName());
	        } else {
	            System.out.println("비밀번호 불일치");
	            return "redirect:/login?error";
	        }
	    } else {
	        System.out.println("사용자 없음");
	        return "redirect:/login?error";
	    }

	    return "redirect:/";
	}
*/	
	
	
	/*
		* @SessionAttributes + model을 통해 로그인 정보를 관리하는 경우
			SessionStatus객체를 통해 사용완료 처리해야 한다.
			- session객체를 폐기하지 않고 재사용
	
	*/
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete())	// status의 상태가 살아있는 상태이면
			status.setComplete();    // status를 컴플릿 해라
		return "redirect:/";
	}
}
