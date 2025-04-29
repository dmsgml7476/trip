package com.trip.repository.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Member.UserLikeEntity;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLikeEntity, Long>{

}
