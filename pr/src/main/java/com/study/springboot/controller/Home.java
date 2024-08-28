package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Reply;
import com.study.springboot.service.ReplyService;

@RestController
public class Home {
	
	@Autowired
	ReplyService replyService;
//	
//	@RequestMapping("/")
//    public String index() {
//        return "forward:/index.html";
//    }
//	
	@GetMapping("/api/mainRply")
	public List<Reply> mainRplyList(){
		return replyService.replyAllList();
	}
	
	@GetMapping("/api/test")
	public String test() {
		return "test입니다";
	}
}
