package com.trip.service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trip.config.auth.CustomUserDetails;
import com.trip.entity.Member.UserEntity;
import com.trip.repository.Member.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	
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
