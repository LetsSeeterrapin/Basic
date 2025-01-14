package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.repository.MemberJdbcRepository;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

// Service는 Comtroller와 Repository사이에서 Dto에서 Member로, Member에서 Dto 변환
@Service
public class MemberService {

    @Autowired
    private MemberJdbcRepository memberRepository;

    public List<MemberListRes> findAll() {
//        List<Member> members = memberMemoryRepository.findAll();
//        List<MemberListRes> memberListResList = new ArrayList<>();
//        for (Member m : members) {
//            memberListResList.add(m.listFromEntity());
//        }
//        return memberListResList;

        return memberMemoryRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());
    }
    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{ //MemberController 51번(회원가입) 에서 호출하는 메서드
        if(memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미존재하는 이메일입니다");
        }
        if(memberCreateDto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다");
        }

        memberMemoryRepository.save(memberCreateDto.toEntity());
    }
    public MemberDetailDto findById(Long id)  throws NoSuchElementException {
//        Optional<Member> optionalmember = memberMemoryRepository.findById(id);
//        Member member = optionalmember.orElseThrow(() ->new NoSuchElementException("없는  ID입니다"));
//        MemberDetailDto dto
//                =member.detailFromEntity();
//        return dto;
        return memberMemoryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("없는ID입니다")).detailFromEntity();
    }
}
