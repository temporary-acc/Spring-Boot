package com.example.demo.economy;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EconomyForm {
	// @NotEmpty => 빈 문자열:""을 허용하지 않는다
	@NotEmpty(message="제목은 필수 입력 항목입니다.")
	// 최대 길이가 128 Byte를 넘으면 않된다
	@Size(max=128)
	private String subject;
	
	// 내용
	@NotEmpty(message="내용은 필수 입력 항목입니다.")
	private String content;
}

