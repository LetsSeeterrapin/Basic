package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberMemoryRepository {
    private List<Member> memberList = new ArrayList<>();

    public static Long id = 1L;
    public List<Member> findAll() {
        return this.memberList;
    }

    public void save(Member member) {
        this.memberList.add(member);
        id++;
    }

    public Optional<Member> findById(Long id) {
        Member member = null;
        for(Member m : memberList) {
            if(m.getId().equals(id)) {
                member = m;
            }
        }
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByEmail(String email) {
        Member member = null;
        for(Member m : memberList) {
            if(m.getEmail().equals(email)) {
                member=m;
            }
        }
        return Optional.ofNullable(member);
    }
}
