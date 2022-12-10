package com.example.demo.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class WebUserController {
	private final WebUserService webUserService;
	// 회원가입화면
	@GetMapping("/signup")
	public String create(WebUserCreateForm webUserCreateForm) {
		return "signup_form";
	}

	// 유효성폼 적용
	@PostMapping("/signup")
	public String signup(@Valid WebUserCreateForm webUserCreateForm, BindingResult bindingResult) {
		// 1. 바딩인과정에서 에러가 발생하면 돌려보낸다
		if( bindingResult.hasErrors() ) { // 오류가 있다면
			return "signup_form";// 다시 회원가입 화면으로 이동->입력값 유지
		}
		// 2. 비밀번호 1 비밀번호 2 둘이 다른지 체크
		if( !webUserCreateForm.getPassword1().equals(webUserCreateForm.getPassword2()) ){
			bindingResult.rejectValue("password2", "passwordInCorrect", 
					"2개의 비밀번호가 서로 일치 하지 않는다");
			return "signup_form";// 다시 회원가입 화면으로 이동->입력값 유지
		}
		
		this.webUserService.create(webUserCreateForm.getUsername(), 
				webUserCreateForm.getPassword1(), 
				webUserCreateForm.getEmail());
		return "redirect:/";
	}
	
	// 로그인....
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
}







