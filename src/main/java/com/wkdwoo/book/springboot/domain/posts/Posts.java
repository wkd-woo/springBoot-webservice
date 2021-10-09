package com.wkdwoo.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타낸다
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙.GenerateType.IDENTITY -> auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타낸다. 굳이 사용하지 않아도 클래스의필드는 모두 칼럼이 된다
    private String title;                 // 그럼에도 사용하는 이유는,기본값 외에 추가로 변경이 필요한 옵션이 있기 때문.

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
