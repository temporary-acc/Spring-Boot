package com.example.demo.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.economy.EconomyNoDataException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WebUserService {
	private final WebUserRepository webUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입처리
	public void create(String name, String password, String email){
		// 1. WebUser 객체 생성
		WebUser user = new WebUser();
		// 2. 데이터 세팅 => 비밀번호는 노출되면 않된다!! => 암호화
		user.setName(name);
		user.setEmail(email);
		user.setPassword( passwordEncoder.encode(password) );
		// 3. 레포티토리.save( WebUser객체 )
		webUserRepository.save( user );		
	}
	
	// 사용자 이름 넣으면 WebUser 객체 리턴
	public WebUser getUser(String username) {
		Optional<WebUser> user = webUserRepository.findByName(username);
		if( user.isPresent() ) {
			return user.get();
		}
		// throw new DataNotFoundException("user not found"); 
		throw new EconomyNoDataException("user not found");
	}

	// 로그인	
	// 로그아웃
}
