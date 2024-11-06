package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// DataNotFoundException 데이터베이스에서 특정 엔티티 또는 데이터를 찾을 수 없을때 발생시키는 예외 클래스 작성
//이 예외가 발생하면 스프링부트는 설정된 HTTP 상태코드(HttpStatus.NOT_FOUND)와 이유(entity not found)를 포함한 응답을 생성하여 클라이언트에세 반환
//RuntimeException 실행시 발생하는 예외
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public DataNotFoundException(String message) {
		super(message);
	}
}
