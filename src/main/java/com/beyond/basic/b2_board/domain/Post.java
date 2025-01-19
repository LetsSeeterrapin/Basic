package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//  1)Entity : id, title, contents, memberId
//2)controller : rest
// -등록 : post/create -> dto : title, contents, memberId
// -목록조회 : post/list -> id, title
// -상세조회 : post/detail/id -> id, title, contents, memberEmail
//3)service(예외throw), repository


@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor// @Builder어노테이션을 사용하여, 빌더패턴으로 엔티티의 생성자를 구성
@Builder            // 빌더패턴의 장점:매개변수의 순서와 개수를 유연하게 세팅 할수 있다.
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String content;

//    lazy로 설정시 member객체를 사용하지않는 한, member테이블로 쿼리 발생하지않음.
//    이에 반해 EAGER타입으로 설정시 사용하지 않아도 member테이블로 쿼리 발생
//    아래 어노테이션과 fk연결객체지정은 필수요소
    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne에서는 default EAGER
    @JoinColumn(name = "member_id") // DB컬럼명을 지정해주는 어노테이션
    private Member member;

    public PostListDto toListDto(){
        return PostListDto.builder().id(this.id).title(this.title).build();
    }

    public PostDetailDto toDetailDto(String email){
        return PostDetailDto.builder().id(this.id).title(this.title).content(this.content)
                .memberEmail(email)
                .build();
    }


}
