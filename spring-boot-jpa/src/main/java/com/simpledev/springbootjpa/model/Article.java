package com.simpledev.springbootjpa.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="article")
public class Article {

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Email
	@NotNull
	private String email;
	@OneToMany (fetch = FetchType.EAGER,mappedBy = "article",cascade=CascadeType.ALL)
	private List<Comment> comment;
	private String title;
	private String content;
	private LocalDate date;
	private Boolean published;
	
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		
		return "email: "+this.getEmail()+" title: "+this.getTitle()+" date: "+this.getDate();
	}
	
	
}
