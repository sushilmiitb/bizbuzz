package com.bizbuzz.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
		use=JsonTypeInfo.Id.CLASS,
		include=JsonTypeInfo.As.PROPERTY,
		property="@class")
public class Message {
	private Long id;
	private Date createdAt;
	private String text;
	private String author;
	
	public Message(Long id, Date createdAt, String text, String author){
		this.id = id;
		this.createdAt = createdAt;
		this.text = text;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
