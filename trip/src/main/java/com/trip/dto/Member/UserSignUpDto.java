package com.trip.dto.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSignUpDto {
	private String loginId;
	private String password;
	private String passwordChk;
	private String email;
	private String tel;
	private String nickname;
	private String address;
	private String hashtagIds;
	private boolean agreedAllAlerts;

}
