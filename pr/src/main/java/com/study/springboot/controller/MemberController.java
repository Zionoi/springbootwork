package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.study.springboot.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	

    // 로그인 세션 확인
//    @GetMapping(path = "me")
//    public ApiResult<UserApiResponse> me(HttpSession session){
//
//        UserApiResponse user = (UserApiResponse) session.getAttribute("user");
//        if(ObjectUtils.isEmpty(user)){
//            throw new UnauthenticatedException();
//        }
//        return response(user)
//        
//    }
//
//    // 로그인
//    @PostMapping("/login")
//    public ApiResult<UserApiResponse> loginUser(HttpSession session, @RequestBody LoginUserForm loginInfo) {
//
//        UserApiResponse user = userService.login(loginInfo);
//        session.setAttribute("user", user);
//        return response(user);
//        
//    }
//
//    //로그아웃
//    @GetMapping("/logout")
//    public ApiResult<Integer> logoutUser(HttpSession session){
//        session.removeAttribute("user");
//        return response(1);
//    }
//
//}
}
