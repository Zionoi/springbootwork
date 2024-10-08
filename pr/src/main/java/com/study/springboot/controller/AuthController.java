package com.study.springboot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;
import com.study.springboot.util.JwtUtil; // JwtUtil 클래스 임포트

@RestController
@CrossOrigin(origins = "http://localhost:3003") // CORS 설정
@RequestMapping("/api/auth") // API 경로 설정
public class AuthController {

    @Autowired
    private MemberService memberService; // 회원 레포지토리 주입

    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        if (memberService.existsById(member.getUserid())) {
            return "User already exists"; // 이미 존재하는 사용자
        }
        memberService.save(member); // 사용자 저장
        return "User registered successfully"; // 등록 성공 메시지
    }

    @PostMapping("/login")
    public List<Object> login(@RequestBody Member loginRequest) {
        Optional<Member> member = memberService.findById(loginRequest.getUserid()); // 사용자 조회
        System.out.println("loginRequest :" + loginRequest);
        
        if (member.isPresent() && member.get().getPass().equals(loginRequest.getPass())) {
            List<Object> list = new ArrayList<>();  // ArrayList로 초기화
            
            // JwtUtil을 사용하여 JWT 생성
            String token = JwtUtil.generateToken(member.get().getUserid());
            System.out.println("token :" + token);
            
            // 리스트에 토큰과 아이디를 추가
            list.add(token);
            list.add(member.get().getUserid());
            
            return list; // JWT와 아이디가 담긴 리스트 반환
        }
        
        return Collections.singletonList("Invalid credentials"); // 잘못된 인증 정보
    }

}