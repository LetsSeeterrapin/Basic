package com.beyond.basic.b1_hello.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

//@Getter
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든매개변수있는 생성자
@Data // Getter, Setter, ToString매서드 까지 포함되어있는 어노테이션이며 다른계층에서 사용예정
public class Hello {
    private String name;
    @Setter // email변수에 관한 setter만 생성
    private String email;
//    private MultipartFile photos;
}
