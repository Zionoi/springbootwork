package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//빈으로 등록된 객체 생성
@Controller
public class MyController {
	// @RequestMapping("/") : 루트 라는뜻(맨처음 띄어지는 화면)
//	@ResponseBody 문자열로 그대로 반환된다는 뜻
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "jsp로 실행";
	}
	
	@RequestMapping("/test1")
	public String test1() {
		return "test1";
		// application.properties에서 
		// spring.mvc.view.prefix=/WEB-INF/views/ 로 설정해서
		// /WEB-INF/views/test1.jsp로 호출됨. 메소드 자료형에 String은 해당 주소를 찾아서 web으로 보여줌
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "/sub/test2";
		// 호출시  /WEB-INF/views/sub/test2.jsp
	}
}
