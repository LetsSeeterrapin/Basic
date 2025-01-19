package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.repository.MemberJpaRepository;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import com.beyond.basic.b2_board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

// Service는 Comtroller와 Repository사이에서 Dto에서 Member로, Member에서 Dto 변환
@Service
// 아래 어노테이션을 통해, 모든 메서드에 트랜잭션을 적용하고, 만약 예외()가 발생시 롤백처리 자동화
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberListRes> findAll() {
//        List<Member> members = memberMemoryRepository.findAll();
//        List<MemberListRes> memberListResList = new ArrayList<>();
//        for (Member m : members) {
//            memberListResList.add(m.listFromEntity());
//        }
//        return memberListResList;
        return memberRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());
    }
    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{ //MemberController 51번(회원가입) 에서 호출하는 메서드
        if(memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미존재하는 이메일입니다");
        }
        if(memberCreateDto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다");
        }
        memberRepository.save(memberCreateDto.toEntity());
    }

    public Member save2(MemberCreateDto memberCreateDto) throws IllegalArgumentException{ //MemberController 51번(회원가입) 에서 호출하는 메서드
        if(memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미존재하는 이메일입니다");
        }
        if(memberCreateDto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다");
        }
        Member member = memberRepository.save(memberCreateDto.toEntity());
        return member;
    }


    public MemberDetailDto findById(Long id)  throws NoSuchElementException, RuntimeException {
//        Optional<Member> optionalmember = memberMemoryRepository.findById(id);
//        Member member = optionalmember.orElseThrow(() ->new NoSuchElementException("없는  ID입니다"));
//        MemberDetailDto dto = member.detailFromEntity();
//        return dto;
        return memberRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("없는ID입니다")).detailFromEntity();
    }
    public void updatePw(MemberUpdateDto memberUpdateDto){
        Member member = memberRepository
                .findByEmail(memberUpdateDto.getEmail())
                .orElseThrow(()->new EntityNotFoundException("없는사용자입니다."));
        member.updatePw(memberUpdateDto.getNewPassword());
//        기존객체를 조회후에 다시 save할 경우에는 insert가 아니라 update쿼리 실행
        memberRepository.save(member);
    }

    public void delete(Long id) {
//        memberRepository.deleteById(id);
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("not found"));
        memberRepository.delete(member);
    }
}
