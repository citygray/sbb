package com.mysite.sbb.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

//회원가입을 위한 엔티티와 서비스 그리고 폼이 준비되었으니, URL 매핑을 위한 User 컨트롤러를 만들어보자

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//bindingResult.rejectValue(필드명, 오류 코드, 오류 메시지)
			bindingResult.rejectValue("passwoerd2", "passWordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		
		userService.create(userCreateForm.getUsername(), userCreateForm.getEmail() ,userCreateForm.getPassword1());
		
		return "redirect:/";
	}
	

}
