package com.trip.service.Member;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.config.auth.CustomUserDetails;
import com.trip.constant.Member.Role;
import com.trip.dto.Member.UserSignUpDto;
import com.trip.entity.Member.HashtagsEntity;
import com.trip.entity.Member.UserAlertSettingEntity;
import com.trip.entity.Member.UserEntity;
import com.trip.entity.Member.UserHashtagEntity;
import com.trip.repository.Member.HashtagsRepository;
import com.trip.repository.Member.UserAlertSettingRepository;
import com.trip.repository.Member.UserHashtagRepository;
import com.trip.repository.Member.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	@Autowired
	private final UserRepository userRepository;
	private final UserAlertSettingRepository userAlertSettingRepository;
	private final PasswordEncoder passwordEncoder;
	private final HashtagsRepository hashtagsRepository;
	private final UserHashtagRepository userHashtagRepository;


	@Transactional
	public void register(UserSignUpDto dto, UserAlertSettingEntity setting) {

	    // 닉네임 null 또는 공백 처리 포함된 정적 메서드 사용
	    UserEntity user = UserEntity.from(dto, passwordEncoder);

	    UserEntity savedUser = userRepository.save(user);

	    setting.setUser(savedUser);
	    userAlertSettingRepository.save(setting);

	    if (dto.getHashtagIds() != null) {
	        for (String idStr : dto.getHashtagIds()) {
	            Long tagId = Long.parseLong(idStr);
	            HashtagsEntity tag = hashtagsRepository.findById(tagId)
	                    .orElseThrow(() -> new IllegalArgumentException("해시태그 없음"));

	            userHashtagRepository.save(
	                UserHashtagEntity.builder()
	                    .user(savedUser)
	                    .hashtags(tag)
	                    .build()
	            );
	        }
	    }
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

	public boolean existsByNickname(String nickname) {
		return userRepository.existsByNickname(nickname);
	}
	

	
	
}
