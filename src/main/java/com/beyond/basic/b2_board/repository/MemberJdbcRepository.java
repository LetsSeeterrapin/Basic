package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class MemberJdbcRepository {

//    Datasource는 DB와 JDBC에서 사용하는 DB연결 드라이버 객체
//    application.yml에서 설정한 DB정보가 Datasource로 주입
    @Autowired
    private DataSource dataSource;

    public List<Member> findAll(){
        return null;
    }
    public void save(Member member){}
    public Optional<Member> findById(Long id){
        return null;
    }
    public Optional<Member> findByEmail(String email){
        return null;
    }


}
