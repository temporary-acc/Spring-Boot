package com.example.demo.economy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.economyComment.Ecomment;
import com.example.demo.user.WebUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EconomyService {
	private final EconomyRepos eRepos;

	public Page<Economy> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("Date")); // or desc
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		// Pageable pageable = PageRequest.of(page, 10);
		Specification<Economy> spec = search(kw);
		return this.eRepos.findAll(spec, pageable);
		// return this.questionRepository.findAllByKeyword(kw, pageable);
	}

	//검색
	private Specification<Economy> search(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Economy> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true); // 중복을 제거
				Join<Economy, WebUser> u1 = q.join("author", JoinType.LEFT);
				Join<Economy, Ecomment> a = q.join("ecommentList", JoinType.LEFT);
				Join<Economy, WebUser> u2 = q.join("author", JoinType.LEFT);
				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), cb.like(q.get("content"), "%" + kw + "%"),
						cb.like(u1.get("name"), "%" + kw + "%"), cb.like(a.get("content"), "%" + kw + "%"),
						cb.like(u2.get("name"), "%" + kw + "%"));
			}
		};
	}

	// 기사 검색
	public Economy getEconomyById(Integer id) {
		Optional<Economy> e = this.eRepos.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		throw new EconomyNoDataException("해당 ID로는 조회되지 않습니다.");
	}

	//등록
	public void create(String subject, String content, WebUser author) {
		Economy e = new Economy();
		e.setSubject(subject);
		e.setContent(content);
		e.setAuthor(author);
		e.setDate(LocalDateTime.now());
		// 등록
		this.eRepos.save(e);
	}

	//수정
	public void modify(Economy e, String s, String c) {
		e.setSubject(s);
		e.setContent(c);
		e.setModifyDate(LocalDateTime.now());
		this.eRepos.save(e);
	}

	// 삭제
	public void delete(Economy e) {
		this.eRepos.delete(e);
	}

}
