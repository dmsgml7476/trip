package com.trip.control.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    @GetMapping("/login")
    public String login(Model model) {
    	return "member/signIn";
    }
    
    // 로그인 실패시
    
    @GetMapping("/login/error")
    public String loginfail(Model model) {
    	model.addAttribute("loginErrorMsg", "아이디와 비밀번호가 일치하지 않습니다");
    	return "member/signIn";
    }
    
    
}
