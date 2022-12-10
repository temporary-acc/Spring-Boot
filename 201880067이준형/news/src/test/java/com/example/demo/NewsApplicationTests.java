package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.economy.Economy;
import com.example.demo.economy.EconomyRepos;
import com.example.demo.economyComment.Ecomment;
import com.example.demo.economyComment.EcommentRepos;
import com.example.demo.user.WebUser;
import com.example.demo.user.WebUserRepository;

@SpringBootTest
class NewsApplicationTests {
    @Autowired
    private EconomyRepos eRepos1;

    @Test
    void test_Economy_Insert() {
        // 테이블에 데이터 집어 넣기 => 회원가입, 글등록,...
        for (int i = 0; i <= 20; i++) {

            Economy e = new Economy(); // 1. 테이블에 1대1로 대응하는 엔티티객체생성
            e.setSubject("테스트");
            e.setContent("테스트 입니다.");
            e.setDate(LocalDateTime.now());
            // 등록
            this.eRepos1.save(e);
        }
    }

    @Autowired
    private EcommentRepos commentRepos;

    @Autowired
    private EconomyRepos economyRepos;

    @Test
    void test_Ecomment_Insert() {
        for (int i = 0; i <= 20; i++) {
        Optional<Economy> a = this.eRepos1.findById(22);
        assertTrue(a.isPresent());
        Economy e = a.get();
        Ecomment comment = new Ecomment();
        comment.setContent("테스트 입니다.");
        comment.setEconomy(e);
        comment.setDate(LocalDateTime.now());
        this.commentRepos.save(comment);
        }
    }


    @Test
    void test_Economy_Select() {
        List<Economy> all = this.eRepos1.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void test_Economy_Select_ID() {
        Optional<Economy> q = this.eRepos1.findById(1);
        if (q.isPresent()) { // 존재하는가?
            Economy economy = q.get();
            assertEquals("test", economy.getSubject());
        }
    }

    @Test
    void test_Economy_Select_Subject() {

        Economy e = this.eRepos1.findBySubject("test");
        assertEquals("test", e.getSubject());

    }


    /**
     * 댓글테이블 관련 테스팅
     */
    @Autowired
    private EcommentRepos eRepos;

    // 1. 특정 질문에 대한 답변 등록
    @Test
    void test_Answert_Insert() {
        // 게시물 획득
        Optional<Economy> q = this.eRepos1.findById(1);
        assertTrue(q.isPresent());
        Economy e = q.get();
        // 댓글 데이터 세팅
        Ecomment comment = new Ecomment();
        comment.setContent("테스트 댓글 내용입니다."); // 댓글내용
        comment.setEconomy(e); // 게시물
        comment.setDate(LocalDateTime.now()); // 등록시간
        // 저장
        this.commentRepos.save(comment);
    }

    // ======================================================================
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    @Test
    void test_WebUser_Insert() {
        WebUser user = new WebUser();
        user.setName("admin");
        user.setEmail("admin@mail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        webUserRepository.save(user);
    }

    // 사용자 이름으로 회원 조회하기
    @Test
    void test_WebUser_SelectByName() {
        Optional<WebUser> user = webUserRepository.findByName("ppp");
        if (user.isPresent()) { // 존재하는가?
            WebUser u = user.get();
            // assertEquals(기대값, 실제값) => 동일하면 성공
            assertEquals("p@p.com", u.getEmail());
        }
    }
}
