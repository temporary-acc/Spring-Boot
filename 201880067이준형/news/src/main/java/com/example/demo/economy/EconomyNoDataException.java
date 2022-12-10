package com.example.demo.economy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 실제로는 => 해당 페이지 없다
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found")
public class EconomyNoDataException extends RuntimeException {
	public EconomyNoDataException(String msg) {
		super(msg);
	}
}
