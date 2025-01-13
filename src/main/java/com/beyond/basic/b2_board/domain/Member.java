package com.beyond.basic.b2_board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;
}
