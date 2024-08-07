package com.study.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.springboot.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	// 임플리를 객체생성 할 필요없이 어노테이션달면 빈으로 관리가 된다.
	@Autowired
	BoardService boardService;
	
	
//	@RequestMapping("/")
//	public String root() throws Exception{
//		return "list";
//	}
	@RequestMapping("/")
	public String root() throws Exception{
		return "redirect:list";
	}
	
	/*
	 	
	 	요청 처리 수 응답페이지로 포워딩 또는 url재요청시 응답 데이터를 담는 방법(요청받은 값 처리후 답할때 담아서 보내는 방법)
		1. Model 객체
			포워딩할 뷰로 전달하고자 하는 데이터를 맵형식<key-value>로 담을 수 있는 영역
			requestScope임. 
			** 단, setAttribute가 아닌 addAttribute메소드 이용
		2. ModelAndView 객체
			Model은 데이터를 맵형식<key-value>로 담을 수 있는 영역
			View는 응답뷰에 대한 정보를 담을 수 있는 공간
	
	*/
	//org.springframework.ui 에 있는 Model 임폴트후 모델객체 사용
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("totalRecord",boardService.totalRecord());
		model.addAttribute("list",boardService.list());
		return "list";
	}
	
	/*
		요청시 전달한값(파라메터)를 받는 방법
		1. HttpServletRequest를 이용하여 전달받는 방법
			메서드의 매개변수에 넣는방법
			ex) 메서드 : 반환형 메서드명(HttpServletRequest request(매개변수))
			
		2. @RequestParam 어노테이션을 사용 하는 방법
			: 메서드 위에 어노테이션을 넣으면 끝
			- 변수에 저장할 때 : request.getParameter("키")
			
		3. 메서드 매개변수에 직접 이름(전달한 키와 동일한 이름)을 넣어줌 ****지금은 안되는것같음*****
	*/
	
//	//1번 방법
//	@RequestMapping("/detail")
//	public String detailView(HttpServletRequest r) {
//		String bno = r.getParameter("boardno");
//		return "detail";
//	}

//	//2번 방법
//	@RequestMapping("/detail")					// 이런식으로 jsp에서 넘어온값을 @RequestParam 으로 받고 오른쪽 변수에 담아줌 디폴트값도 설정가능
//	public String detailView(@RequestParam(value="boardno") String bno,
//							 @RequestParam(value="writer", defaultValue="홍길동") String w ){
//		return "detail";
//	}
	
//	//3번 방법
//	@RequestMapping("/detail")			// 이렇게 받을땐 앞에서 넘겨준 키값과 동일하게 변수이름을 넣어야 함. default사용 못함
//	public String detailView(String boardno, String writer ) {
//		
//		return "detail";
//	}
//	
	
	//1번방법 사용
	@RequestMapping("/detail")			// 
	public String detailView(HttpServletRequest r, Model model) {
		String bno = r.getParameter("boardno");
		System.out.println(bno);
		model.addAttribute("detailBoard", boardService.detailBoard(bno));
		return "detail";
	}
	
	@RequestMapping("/delete")
	public String deleteBoard(@RequestParam(value="boardno") String bno, Model model) {
		int result = boardService.deleteBoard(bno);
		if(result > 0) {
			return "redirect:list";
		}else {
			return "detail";
		}	
	}
	
	@RequestMapping("/writerForm")
	public String writerForm() {
		
		return "writerForm";
	}
	
	@RequestMapping("/write")
	public String writer(HttpServletRequest request) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
				
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", writer);
		map.put("title", title);
		map.put("content", content);
		
		boardService.insertBoard(map);
		
		return "redirect:list";
	}
	
	
	
}
