package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionForm;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	//@Transactional 애너테이션을 사용하면 메서드가 종료 될때 까지 DB세션이 유지된다
	@Transactional
	@Commit
	void testJpa() {
		//save
		/*
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("본문");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		*/
		
		
		//findAll
		//jUnit assertEquals(기댓값, 실제값);
		/*
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
		
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?",q.getSubject());
		*/
		
		
		//findById
		//Option은 그 값을 처리 하기 위한(null을 유연하게 처리)클래스로, isPresent()메서드로 값이 존재를 확인
		/*
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
		*/
		
		
		//findBySubject
		//리포지터리의 메서드명을 분석하여 쿼리생성
		/*
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1,q.getId());
		*/
		
		//And
		/*
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","본문");
		assertEquals(1,q.getId());
		*/
		
		//Like
		/*
		Question q = this.questionRepository.findBySubjectLike("sbb가%");
		assertEquals(1,q.getId());
		*/
		
		//Between
		/*
		LocalDateTime toDate = LocalDateTime.now();
		LocalDateTime fromDate = toDate.minusDays(3);
		List<Question> list = this.questionRepository.findByCreateDateBetweenOrderByCreateDateDesc(fromDate, toDate);
		//assertEquals(3, list.size());
		
		for(Question question : list) {
			System.out.print(question.getId()+"\t");
			System.out.print(question.getSubject()+"\t");
			System.out.print(question.getCreateDate()+"\n");
		}
		*/
		
		//LessThan
		/*
		List<Question> list = this.questionRepository.findByIdLessThan(2);
		assertEquals(3, list.size());
		*/
		
		//GreaterThanEqual
		/*
		List<Question> list = this.questionRepository.findByIdGreaterThanEqual(2);
		assertEquals(3, list.size());
		*/
		
		//In
		/*
		String[] subjectList = new String[]{"sbb가 무엇인가요?","제목없음"};
		List<Question> list = this.questionRepository.findBySubjectIn(subjectList);
		
		for(Question question : list) {
			System.out.println(question.getSubject());
		}
		*/
		
		//질문 엔티티 수정
		/*
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		//oq는 Optional<Question> 타입이고, oq.get()은 이 Optional에서 Question 객체를 추출하는 것
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
		*/
		
		//질문 엔티티 삭제
		/*
		assertEquals(2,this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1,this.questionRepository.count());
		*/
		
		//답변데이터 저장하기
		/*
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네, 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		
		this.answerRepository.save(a);
		*/
		
		//답변데이터 조회하기
		/*
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2,a.getQuestion().getId());
		*/
		
		//질문 데이터를 통해 답변 데이터 찾기
		/*
		 * Optional<Question> oq = this.questionRepository.findById(3);
		 * assertTrue(oq.isPresent()); Question q = oq.get();
		 */
		
		//답변추가
		/*
		Answer a1 = new Answer();
		a1.setContent("추가 답변입니다.");
		a1.setCreateDate(LocalDateTime.now());
		a1.setQuestion(q);
		this.answerRepository.save(a1);
		*/
		
		//List<Answer> answerList = q.getAnswerList();
		//assertEquals(1, answerList.size());
		
		/*
		 * for(Answer answer : answerList) { System.out.print(answer.getId()+"\t");
		 * System.out.print(answer.getContent()+"\t");
		 * System.out.print(answer.getCreateDate()+"\n"); }
		 */
		
		/* 
		for (int i = 1; i <= 300; i++) {
			 String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용 없음";
			QuestionForm qf = new QuestionForm();
			qf.setSubject(subject);
			qf.setContent(content);
			qf.setCategoryId(1);
			this.questionService.create(qf);
		}
		*/
		
		 //Question findBySubjectLike(String subject);
		/*
		List<Question> qList = this.questionRepository.findBySubjectLike("테스트 데이터입니다%");
		Question q = qList.get(0);
		assertEquals("테스트 데이터입니다:[001]",q.getSubject());
		System.out.println(q.getSubject());
		*/
		
		//질문 데이터 수정하기
		//엔티티 데이터를 수정하는 테스트 코드 작성
		//Optional은 그 값을 처리하기 위한(null값을 유연하게 처리하기 위한) 클래스로, isPresent()메서드로 값이 존재하는지 확인하수 있다.
		/*
		Optional<Question> oq = this.questionRepository.findById(3);
		//assertTrue()는 괄호 안에 값이 true 인지 테스트 하고 
		//oq.isPresent()가 false를 리턴하면 오류가 발생라고 테스트 종료
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목3");
		this.questionRepository.save(q);
		*/
		
		/*
		if(oq.isPresent()) {
			Question q = oq.get();
			q.setSubject("수정된 제목");
			this.questionRepository.save(q);
		}
		*/
		
		//질문 데이터 삭제하기
		/*
		//long count = this.questionRepository.count();
		//System.out.println(count);
		
		assertEquals(307, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(306, this.questionRepository.count());
		*/
		
		//답변 데이터 저장하기
		/*
		Optional<Question> oq = this.questionRepository.findById(5);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("서서 잘 수 도 있어요");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		*/
		
		//답변데이터 조회하기
		//id값이 1인 답변을 조회했다. 그리고 조회한 답변과 연결된 질문의 id가 2인지도 조회해 보았다. 테스트는 오류 없이 잘 통과될 것이다.
		Optional<Answer> oa = this.answerRepository.findById(8);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(5, a.getQuestion().getId());
		
		//https://wikidocs.net/160890#_5
		//답변데이터를 통해 질문데이터 찾기
		
		//질문 데이터를 통해 답변 데이터 찾기
		Optional<Question> oq = this.questionRepository.findById(5);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1,answerList.size());
		assertEquals("서서 잘 수 도 있어요",answerList.get(0).getContent());

		
	}

}
