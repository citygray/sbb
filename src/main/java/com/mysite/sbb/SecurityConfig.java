package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //모든 요청 URL이 스프링 시큐리티의 제러를 받도록 만드는 애너테이션
public class SecurityConfig {
	/*
	 * 빈이란?
	 * 빈은 스프링에 의해 생성 또는 관리되는 객페를 의미
	 * 우리가 지금껏 만들어 왔던 컨트롤러, 서비스, 리포지터리 등고 모두 빈에 해당
	 * @Bean 애너테이션을 통해 자바코드 내에서 별도로 빈을 정의하고 등록할 수 있다.
	 * */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
		return http.build();
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		//스프링 시큐리티의 BCryptPasswordEncoder 를래스를 사용하여 암호화
        return new BCryptPasswordEncoder();
    }

}
