package com.mysite.sbb.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

//회원가입을 위한 폼 클래스
@Getter
@Setter
public class UserCreateForm {
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 항목입니다.")
	private String password2;

	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email //해당 속성값이 이메일 형식과 일치하는지를 검증
	private String email;
	
}
