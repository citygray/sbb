package com.mysite.sbb.question;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
//Model 객체는 자바 클래스(Java class)와 템플릿(template) 간의 연결 고리 역할
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.category.CategorySevice;

import lombok.RequiredArgsConstructor;

//DTO 사용이유
/*
 * 엔티티 클래스는 데이터 베이스와 직접 맞닿아 있는 클래스이므로 컨트롤러 또는 타임리프와 같은 템플릿엔진에 전달해 사용하는 것은 좋지 않다.
 * 왜냐하면 엔티티객체에는 민감한 데이터가 포함될 수 있기 때문
 * 그래서 Question,Answer 엔티티를 대신해 사용한 DTO(Data Transfer Object)클래스가 필요하다. 
 * 그리고 Question,Answer 등의 엔티티 객체를 DTO객체로 변환하는 작업도 필요하다. 
 * 그러면 엔티티 객체를 변환하는 일은 어디서 처리해야할까? 바로 서비스
 * 서비스는 컨트롤러와 리포지터리의 중간에서 엔티티와 DTO 객체를 서로 변환하여 양방향으로 전달하는 역할을 한다.
 * */

//URL 프리픽스
@RequestMapping("/question")
//**생성자 주입(@RequiredArgsConstructor)**을 사용하여 의존성을 주입하는 것이 더 좋은 접근 방식입니다.
@RequiredArgsConstructor //클래스 내에서 final로 선언된 모든 필드를 인자로 가지는 생성자를 자동으로 생성
@Controller
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;// final로 선언된 필드
	private final CategorySevice categorySevice;// final로 선언된 필드
	
	/*
	@GetMapping("/list")
	//@ResponseBody //메서드의 반환값을 HTTP 응답 바디에 직접담아 전송
	public String list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList",questionList);
		return "question_list2";
	}
	*/
	
	
	@GetMapping("/list") public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) { //매개변수로 Model를 지정하면 객체가 자동으로 생성 
		Page<Question> paging = this.questionService.getList(page); 
		model.addAttribute("paging",paging);
	
		return "question_list"; 
	}
	
	
	//url에서 값을 얻기
	// @GetMapping에 {}안에 키 와 @PathVariable에 매개변수 이름이 갇아야한다.
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "question_detail";
	}
	//파라미터에 QuestionForm questionForm 넣는 이유?
	//question_from.html은 [질문 등록하기] 버튼을 통해 GET방식으로 URL이 요청되더라고 th:object에 의해 QuestionForm 객체가 필요  
	@GetMapping("/create")
	public String questionCreate(Model model) {
		model.addAttribute("questionForm",new QuestionForm()); 
		model.addAttribute("categories",categorySevice.getList());
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm);
		return "redirect:/question/list";
	}

}
