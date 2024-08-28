package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Menu;
import com.study.springboot.service.MenuService;

//글씨로(제이슨으로) 반환해주기위한 RestController 어노테이
@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/test")
	public String hello() { 
		return "테스트 입니다";
	}
	
	@GetMapping("/menuall")
	public List<Menu> menuAllList() {
		
		return menuService.menuAllList();
	}
	
	@PostMapping("/addmenu")
	public String addManu(@RequestBody Menu m) { // 정보 넘겨받을땐 @RequestBody로 받아준다
		System.out.println("서버로 데이터 보내기 : " + m);
		Menu menu = menuService.insertMenu(m);
		return "ok";
	}
	

}
