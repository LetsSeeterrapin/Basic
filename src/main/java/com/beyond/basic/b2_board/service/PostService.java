package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListDto;
import com.beyond.basic.b2_board.repository.MemberRepository;
import com.beyond.basic.b2_board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public void save(PostCreateDto postCreateDto) {
        Member member = memberRepository.findById(postCreateDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Post post = postCreateDto.toEntity(member);
        postRepository.save(post);
    }

    public  List<PostListDto> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p->p.toListDto()).collect(Collectors.toList());
    }

    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Post not found"));
//        Post에 member객체생성했기때문에 찾아올필요X
//        Member member = memberRepository.findById(post.getMemberId())
//                .orElseThrow(()->new EntityNotFoundException("Member not found"));
        return post.toDetailDto(post.getMember().getEmail());
    }

//    public List<Post> findAll() {
//        return postRepository
//    }
}
