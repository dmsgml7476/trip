package com.trip.control.Member;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.dto.Member.UserSignUpDto;
import com.trip.entity.Member.UserAlertSettingEntity;
import com.trip.service.Member.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
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
    
    @GetMapping("/terms")
    public String join() {
    	return "member/terms";
    }
    
    @GetMapping("/signUp")
    public String joinform(Model model) {
    	model.addAttribute("userSignUpDto", new UserSignUpDto());
    	return "member/signUp";
    }
    
    @PostMapping("/signUp")
    public String signup(@ModelAttribute UserSignUpDto dto, @RequestParam(required =false) String alertAgree) {
    	boolean agree = "true".equals(alertAgree);
    	
    	UserAlertSettingEntity setting = UserAlertSettingEntity.builder()
    			.commAlert(agree)
    			.tripAlert(agree)
    			.build();
    	
    	userService.register(dto, setting);

        return "redirect:/login";
    }
    
    @GetMapping("/api/check-id")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam("loginId") String loginId) {
        boolean exists = userService.existsByLoginId(loginId);
        return Map.of("available", !exists);
    }
    
    
    
}
