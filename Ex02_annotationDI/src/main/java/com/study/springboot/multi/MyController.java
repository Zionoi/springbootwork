package com.study.springboot.multi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
	어떤 기능을 수행하는 경우는 @Controller (서블릿) 매핑을 보통 붙이고 그걸보고 찾아감.
	의존성 주입에 사용되는 경우는 @Component
	
	둘다 빈으로 등록하시오라는 뜻

*/
@Controller	//컨트롤러를 빈으로 등록할땐  @Controller 어노테이션을 사용한다.// 어떠한 기능을 수행하는 경우는  @Controller를 붙임
public class MyController {
	@Autowired
	Person Person1;
	@Autowired
	@Qualifier("printerB")
	Printer printer;
	@Autowired
	Person Person2;
	
	@RequestMapping("/") // doGet, doPost
	public @ResponseBody String root() {  // 되돌려주는 값
		return "AnnotationDI @ResponseBody는 어떤 자료형이든 문자열 그대로 반환함";
	}
		
	
	

}
