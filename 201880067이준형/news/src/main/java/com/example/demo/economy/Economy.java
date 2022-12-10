package com.example.demo.economy;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.economyComment.Ecomment;
import com.example.demo.user.WebUser;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Economy {
	/* 경제 카테고리 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime date;

	// 기사 댓글
	@OneToMany(mappedBy = "economy", cascade = CascadeType.REMOVE)
	private List<Ecomment> ecommentList;

	// 기사 수정 시간
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private WebUser author;
}
