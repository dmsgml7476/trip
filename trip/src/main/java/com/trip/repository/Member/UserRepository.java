package com.trip.repository.Member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Member.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByLoginId(String loginId);
	
	Optional<UserEntity> findByNickname(String nickname);
	
	boolean existsByLoginId(String loginID);
	
	boolean existsByNickname(String nickname);
	
}
