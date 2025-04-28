package com.trip.repository.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip.entity.Member.UserAlertSettingEntity;

@Repository
public interface UserAlertSettingRepository extends JpaRepository<UserAlertSettingEntity, Long>{

}
