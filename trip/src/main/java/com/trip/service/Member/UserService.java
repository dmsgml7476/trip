package com.trip.service.Member;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trip.config.auth.CustomUserDetails;
import com.trip.constant.Member.Role;
import com.trip.dto.Member.UserSignUpDto;
import com.trip.entity.Member.UserAlertSettingEntity;
import com.trip.entity.Member.UserEntity;
import com.trip.repository.Member.UserAlertSettingRepository;
import com.trip.repository.Member.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	private final UserAlertSettingRepository userAlertSettingRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public void register(UserSignUpDto dto, UserAlertSettingEntity setting) {
		
		UserEntity user = UserEntity.builder()
				.loginId(dto.getLoginId())
				.password(passwordEncoder.encode(dto.getPassword()))
				.nickname(dto.getNickname() != null ? dto.getNickname() : dto.getLoginId())
				.role(Role.USER)
				.createdTime(LocalDateTime.now())
				.build();
		
		UserEntity savedUser=userRepository.save(user);
		
		setting.setUser(savedUser);
		
		userAlertSettingRepository.save(setting);
				
	}
	
	public boolean existsByLoginId(String loginId) {
	    return userRepository.existsByLoginId(loginId);
	}
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		// 스프링 시트 로그인, 아이디로 회원 테이블에서 정보 조회
		
		UserEntity user = userRepository.findByLoginId(loginId);
		
		if(user==null) {
			throw new UsernameNotFoundException(loginId);
		}
		
		return new CustomUserDetails(user);
	}
	

	
	
}
