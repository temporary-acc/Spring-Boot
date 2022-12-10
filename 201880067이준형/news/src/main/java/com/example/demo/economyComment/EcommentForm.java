
package com.example.demo.economyComment;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EcommentForm {
	@NotEmpty(message="내용은 필수 입력 항목입니다.")
	private String content;
	
}
