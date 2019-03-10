package com.simpledev.springbootjpa.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Email
	@NotNull
	private String email;
	private String message;
	private LocalDate date;	
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name="article_id",referencedColumnName="id")	
	private Article article;
	
	public Long getId() {
		return id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public void setId(long i) {
		this.id = i;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
//	public Article getArticle() {
//		return article;
//	}
//	public void setArticle(Article article) {
//		this.article = article;
//	}
	
	
}
