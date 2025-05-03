package com.trip.dto.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSignUpDto {
	@Size(min=6, max=16, message="아이디는 6자 이상 20자 이하로 입력해주세요.")
	@NotBlank(message="아이디를 입력하여주세요.")
	@Pattern(regexp="^[a-z0-9_]+$", message="아이디는 소문자, 숫자, 언더바(_)만 사용가합니다.")
	private String loginId;
	private String password;
	private String email;
	private String tel;
	private String nickname;
	private String address;
	private String hashtagIds;
	private boolean agreedAllAlerts;

}
