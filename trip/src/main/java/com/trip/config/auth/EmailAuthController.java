package com.trip.config.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/email")
@RequiredArgsConstructor
public class EmailAuthController {
	
	private final JavaMailSender mailSender;

    
    @PostMapping("/send")
    public Map<String, String> sendEmailCode(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        session.setAttribute("authCode", code);
        
        
        
        try {
        	SimpleMailMessage message=new SimpleMailMessage();
        	
        	message.setTo(email);
        	message.setSubject("Let's Trip 회원가입 인증번호입니다");
        	message.setText("인증번호는 [" + code + "] 입니다.");
        	mailSender.send(message);
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	Map<String, String> error = new HashMap<>();
        	error.put("status", "fail");
        	return error;
        }

        // TODO: 이메일 전송 로직 추가 (이메일 서비스 활용)
        System.out.println("[DEBUG] 전송 대상 이메일: " + email);
        System.out.println("[DEBUG] 인증번호: " + code);

        Map<String, String> response = new HashMap<>();
        response.put("status", "sent");
        return response;
    }


    @PostMapping("/verify")
    public Map<String, Boolean> verifyCode(@RequestBody Map<String, String> request, HttpSession session) {
        String inputCode = request.get("code");
        String savedCode = (String) session.getAttribute("authCode");

        boolean success = inputCode != null && inputCode.equals(savedCode);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        session.setAttribute("authSuccess", success);
        System.out.println("[DEBUG] 이메일 인증 결과: " + success);
        return response;
    }
	
}
