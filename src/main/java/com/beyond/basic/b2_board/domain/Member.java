package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@ToString
//JPA의 엔티티매니저에게 객체를 위임하려면 @Entity어노테이션 필요
@Entity
public class Member extends BaseTimeEntity{
    @Id //pk설정
//    identity : auto_increment설정, GenerationType.AUTO는 jpa에게 적절한 전략을 위임하는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    String은 별다른 설정이 없을 경우 varchar(255)로 DB컬럼으로 설정된다. 변수명 =컬럼명으로 변환.
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    //    @Column(name = "pw") 이렇게 할 수는 있으나, 되도록이면 컬럼명과 변수명을 일치시키는 것이 개발의 혼선을 줄일 수 있음
    private String password;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePw(String newPassword){
        this.password = newPassword;
    }


    public MemberListRes listFromEntity(){
        return new MemberListRes(this.id, this.name, this.email);
    }

    public MemberDetailDto detailFromEntity(){
        return new MemberDetailDto(this.name, this.email, this.password);
    }
}