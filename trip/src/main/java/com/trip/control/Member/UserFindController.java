package com.trip.control.Member;


import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trip.entity.Member.UserDetailEntity;
import com.trip.repository.Member.UserDetailRepository;


import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequiredArgsConstructor

public class UserFindController {
	
    private final UserDetailRepository userDetailRepository;

    @GetMapping("/api/find-id")
    public Map<String, String> findLoginId(@RequestParam("email") String email) {
        Optional<UserDetailEntity> userOpt = userDetailRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            return Map.of("loginId", userOpt.get().getUser().getLoginId());
        } else {
            return Map.of("loginId", "");
        }
    }

}
