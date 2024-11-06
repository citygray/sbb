package com.mysite.sbb.question;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mysite.sbb.category.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	@NotEmpty(message="제목은 필수항목입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용은 필수항목입니다.")
	private String content;
	
	@NotNull(message = "카테고리를 선택해야 합니다.")
	private Integer categoryId; 
}

/*Spring Boot Validation
 * @Size	문자 길이 제한
 * @NotNull	Null을 허용하지 않음
 * @NotEmpty	Null또는 빈 문자열""을 허용하지 않음
 * @Past	과거 날짜만 입력
 * @Future	미래 날짜만 입력
 * @FutureOrPresent 미래 또는 오늘 날짜만 입력
 * @Max	최대값이하의 값만 입력
 * @Min	최소값 이상의 값만 입력
 * @Pattern	입력값을 정규식 패턴으로 검증
 * */
 