package com.example.demo.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 실습 1 : 해당 클레스를 컨트롤로 만들어라. /tsbw 라우팅 처리하여
// "홈페이지" 문고 출력
// 스토리보드 -> 서비스의 메인 줄기 나눠서 -> 업무별로 컨트롤러 생성
@Controller
public class MainController {
	@RequestMapping("/")
	public String main() {
		return "index";
	}
}
