package com.example.demo.economyComment;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.demo.economy.Economy;
import com.example.demo.user.WebUser;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Ecomment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime date;

	@ManyToOne
	private Economy economy;

	@ManyToOne
	private WebUser author;

	private LocalDateTime modifyDate;

	
}
