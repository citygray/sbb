package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//Jpa리포지터리는 엔티티로 테이블을 구성하여 데이터를 관리하도록 도와줌
//JpaRepository JPA가 제공하는 인터페이스 중 하나로 CRUD작업을 처리하는 메서드들이 이미 내장하고 있어 데이터 관리 작업을 좀 더 편하게 처리
//Question 엔티티로 리포지터리 생성
//Question 엔티티의 기본키가 Integer임
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	Page<Question> findAll(Pageable pageable);
	
	//리포지터리의 메서드명을 분석하여 쿼리생성
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject,String content);

	/*
	 * 리포지터리 메서드 조합
	 * And
	 * Or
	 * Between	findByCreateDateBetween(LocalDateTime fromDate,LocalDateTime toDate)
	 * LessThan	findByIdLessThan(Integer Id) Id열에서 조건보다 작은 데이터 조회
	 * GreaterThanEqual	findByIdGreaterThanEqual(Integer Id) Id열에서 조건보다 크거나 같은 데이터 조회
	 * Like	findBysubjectLike(String subject) Subject 열에서 문자열 'subject'와 같은 문자열을 포함한 데이터 조회
	 * In findBySubjectIn(String[] subjects) Subject 열에 데이터가 주어진 배열에 포함는 데이터만 조회
	 * OrderBy findBySubjectOrderByCreateDateAsc(String subject)
	 */
	
	List<Question> findByCreateDateBetweenOrderByCreateDateDesc(LocalDateTime fromDate,LocalDateTime toDate);
	List<Question> findByIdLessThan(Integer Id);
	List<Question> findByIdGreaterThanEqual(Integer Id);
	List<Question> findBySubjectIn(String[] subject);
	List<Question> findBySubjectLike(String subject);
	
}
