package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.NoSuchElementException;
// 모든 @GetMapping 어노테이션은 url요청에 응답하는것.
// 즉, url주소가 같은 @GetMapping을 찾아 그 아래에있는 메서드의 내용을 실행.
// 아래 메서드들은 대부분 html파일을 보냄으로써 요청에응답

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
////    의존성주입(DI) 방법1. Autowired 어노테이션 사용 : 필드주입
//    @Autowired
//    private MemberService memberService;

//    의존성주입(DI) 방법2. 생성자주입방식(가장많이 사용하는 방식)
//        장점1) final을 통해 상수로 사용가능(안정성 향상)
//        장점2) 다형성 구현가능
//        장점3)순환참조컴파일타임에 check
//    private final MemberService memberService;
////      싱글톤 객체로 만들어지는 시점에 아래 생성자가 호출. 생성자가 하나밖에 없을 때 에는 Autowired생략가능
//    @Autowired
//    public MemberController(MemberService memberService) {
//    this.memberService = memberService;
//    }

//    의존성주입(DI) 방법3 RequiredArgs 어노테이션 사용방법
//    RequiredArgs : 반드시 초기화 되어야 하는 필드(final 키워드 등)를 대상으로 생성자를 자동으로 만들어주는 어노테이션
    private final MemberService memberService;


//    홈화면
    @GetMapping("")
    public String memberHome() {
        return "/member/home";
    }

//    회원목록조회
    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberListRes> memberListRes = memberService.findAll();
        model.addAttribute("memberList", memberListRes);
        return "/member/member-list";
    }

//    회원상세조회
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model) {
//        name, email, password
        try {
            MemberDetailDto dto = memberService.findById(id);
            model.addAttribute("member", dto);
                return "/member/member-detail";
            }catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/member-error";
            }catch (RuntimeException e){
                model.addAttribute("errorMessage", e.getMessage());
                return "/member/member-error";
        }
    }

//    회원가입
    @GetMapping("/create")
    public String memberCreate() {
        return "/member/member-create";
    }

    @PostMapping("/create") // Post 요청과 메서드를 맵핑(연결)하는 어노테이션.
//                            // Post 요청 : 클라이언트가 서버로 데이터를 전송할때 쓰는 HTTP메서드,
//                               보통 폼데이터 전송, 파일 업로드, 데이터 생성 등의 용도
    public String memberCreatePost(@ModelAttribute MemberCreateDto memberCreateDto, Model model) {

        try {
            memberService.save(memberCreateDto); // 여기서 Dto형태의 데이터를 Service로 보내면,
//                                              Service에선 데이터를 변환시켜 Repository로 전송.
                                                // 회원정보를 DB로보내기위한 시작점.
            return "redirect:/member/list"; //redirect : 요청을다시보내도록 지시.
                                             //회원가입을 완료한 후에 회원목록페이지로 자동 이동시키기위한 로직
        }
        catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/member/error";
        }
    }
}
