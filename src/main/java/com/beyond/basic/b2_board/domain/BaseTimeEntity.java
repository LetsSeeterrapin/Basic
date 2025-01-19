package com.beyond.basic.b2_board.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
// 기본적으로 Entity는 상속이 불가능하여 MappedSuperclass 붙여야 Entity와의 상속관계가 성립가능
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
