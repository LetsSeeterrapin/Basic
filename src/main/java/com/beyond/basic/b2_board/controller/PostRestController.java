package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.CommonDto;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListDto;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//  1)Entity : id, title, contents, memberId
//2)controller : rest
// -등록 : post/create -> dto : title, contents, memberId
// -목록조회 : post/list -> id, title
// -상세조회 : post/detail/id -> id, title, contents, memberEmail
//3)service(예외throw), repository
@RestController
@RequestMapping("/post/rest")
public class PostRestController {
    private final PostService postService;
    public PostRestController(PostService postService) {
        this.postService = postService;
    }
    //    등록
    @PostMapping("/create")
//    VAlid와 NotEmpty 등 검증 어노테이션이 한쌍
    public ResponseEntity<?> postCreate(@Valid @RequestBody PostCreateDto dto) {
        postService.save(dto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(),
                "post create successful", "OK"),
                HttpStatus.CREATED);
    }

//    목록조회
    @GetMapping("/list")
    public ResponseEntity<?> postList() {
        List<PostListDto> postListDtos = postService.findAll();
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "posts are found", postListDtos),
                HttpStatus.OK);
    }

//    상세조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable long id) {
        PostDetailDto detailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(HttpStatus.OK.value(),
                "post is found", detailDto),
                HttpStatus.OK);
    }





}
