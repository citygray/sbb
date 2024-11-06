package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@GetMapping("/sbb")
	@ResponseBody //URL요청애 대한 응답으로 문자열을 리턴한다.
	public String inde() {
		//System.out.println("index");
		return "index";
	}
	
	//루트 URL 사용
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}


/*
 * 데이터를 관리하는데 사용하는 ORM의 자바 클래스를 엔티티
 * 엔티티는 데이터베이스의 테이블과 매핑되는 자바클래스
 * */
