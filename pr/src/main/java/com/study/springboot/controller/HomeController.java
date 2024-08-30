package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Reply;
import com.study.springboot.service.ReplyService;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	ReplyService replyService;
//	
//	@RequestMapping("/")
//    public String index() {
//        return "forward:/index.html";
//    }
	
	@GetMapping("/mainRply")
	public List<Reply> mainRplyList(){
		return replyService.replyAllList();
	}
	
	@GetMapping("/test")
	public String test() {
		return "test입니다";
	}
	
	@PostMapping("/insertComent")
	public String insertComent(@RequestBody Reply re) {
		System.out.println("re : " + re);
		re.setRefBno((long) 100);
		replyService.insertReply(re);
		return "ok";
	}
}
