package com.study.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.util.JwtUtil; // JwtUtil 클래스 임포트

@RestController
@CrossOrigin(origins = "http://localhost:3003") // CORS 설정
@RequestMapping("/api/auth") // API 경로 설정
public class AuthController {

    @Autowired
    private MemberRepository memberRepository; // 회원 레포지토리 주입

    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        if (memberRepository.existsById(member.getUserid())) {
            return "User already exists"; // 이미 존재하는 사용자
        }
        memberRepository.save(member); // 사용자 저장
        return "User registered successfully"; // 등록 성공 메시지
    }

    @PostMapping("/login")
    public String login(@RequestBody Member loginRequest) {
        Optional<Member> member = memberRepository.findById(loginRequest.getUserid()); // 사용자 조회
        System.out.println("loginRequest :"+ loginRequest);
        if (member.isPresent() && member.get().getPass().equals(loginRequest.getPass())) {
            // JwtUtil을 사용하여 JWT 생성
            String token = JwtUtil.generateToken(member.get().getUserid());
            System.out.println("token :"+ token);
            return token; // JWT 반환
        }
        return "Invalid credentials"; // 잘못된 인증 정보
    }
}
