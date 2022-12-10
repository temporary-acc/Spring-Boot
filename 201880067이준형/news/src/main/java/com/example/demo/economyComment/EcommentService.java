package com.example.demo.economyComment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DataNotFoundException;
import com.example.demo.economy.Economy;
import com.example.demo.user.WebUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EcommentService {
    //Service -> Repository -> DB
	private final EcommentRepos eCommentRepos;
    //데이터 목록
	public List<Ecomment> getList(){
		return this.eCommentRepos.findAll();
	}

    //댓글등록시=> 클래스 => 레파지토리 저장=> DB
	public void create(Economy e, String content, WebUser author){
		Ecomment eComment = new Ecomment();
		eComment.setEconomy(e);
		eComment.setContent(content);
		eComment.setDate(LocalDateTime.now());
		eComment.setAuthor(author);
		this.eCommentRepos.save(eComment);
	}

    //댓글 목록=> 해당 글에 대한 (id)를 통해 댓글 목록 가져옴
    public Ecomment getEcomment(Integer id) {
        //id값 가져옴 Optional클래스로 null이더라도 오류가 발생하지 않게처리
        Optional<Ecomment> e = this.eCommentRepos.findById(id);
        //해당 댓글이 있다면 가져오고 없으면 ...찾을수 없다고 예외 발생
        if (e.isPresent()) {
            return e.get();
        } else {
            throw new DataNotFoundException(" not found");
        }
    }
    // 댓글 수정
    public void modify(Ecomment ecomment, String content) {
    	ecomment.setContent(content);
        ecomment.setModifyDate(LocalDateTime.now());
        this.eCommentRepos.save(ecomment);
    }
    // 댓글 삭제
    public void delete(Ecomment ecomment) {
        this.eCommentRepos.delete(ecomment);
    }
}










