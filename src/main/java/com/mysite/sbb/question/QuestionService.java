package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //리쿼어드 아그스 컨스트럭터
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(QuestionForm questionForm) {
		
		Question question = new Question();
		question.setContent(questionForm.getContent());
		question.setSubject(questionForm.getSubject());
		question.setCreateDate(LocalDateTime.now());
		question.setCategoryId(questionForm.getCategoryId());
		
		this.questionRepository.save(question);
	}
	
}