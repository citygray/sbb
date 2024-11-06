package com.mysite.sbb.question;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysite.sbb.answer.Answer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

//엔티티로 테이블 구성
//엔티티는 데이터베이스 테이블과 매핑되는 자바 클래스
//엔티티를 모델 또는 도메인 모델이라고도 한다, 테이블과 매핑되는 클래스 모두
@Getter
//일반적으로 엔티티를 만들 때에는 setter 메서드를 권히지 않는다. 왜냐하면 엔티티를 데이터 베이스와 바로 연결되므로 데이터를 자유롭게 변경 랗 수 있는 setter 메서드를 허용하는것은 아넞하지 않다.
//그렇다면 setter 메서드 없이 어떻게 엔티티에 값을 저장할 수 있을까?
//엔티티는 생성자에 의해서만 값을 저장 할 수 있게 하고, 테이터를 변경해야할 경우에는 메서드를 추가도 작성
// 이책은 복잡도를 낮추기 위해 setter 추가
@Setter
@Entity
public class Question {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id; 

    @Column(length = 200) 
    private String subject; 

    @Column(columnDefinition = "TEXT") 
    private String content; 

    private LocalDateTime createDate; 

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) 
    private List<Answer> answerList; 
    
    private Integer categoryId;
}
