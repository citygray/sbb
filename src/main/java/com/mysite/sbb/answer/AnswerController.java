package com.mysite.sbb.answer;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final AnswerService answerService;
	private final QuestionService questionService;

	@PostMapping("/create/{id}")
	//@RequestParam(value="content") String content 템플릿의 담변으로 입력한 내용을 얻어옴
	// question_detail.html 에서 <textare>의 name 속성명과 RequestParam에 변수명과 일치 
	//public String createAnswer(Model model,@PathVariable("id") Integer id,@RequestParam(value="content") String content) {
	
	//validation 적용
	public String createAnswer(Model model,@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindingResult) {
		Question question = this.questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question",question);
			return "question_detail";
		}
		
		this.answerService.create(question,answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}
	
}
