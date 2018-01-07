package com.lms.domain;

public class BookSearchParams {

	private String title;
	private String author;
	
	public BookSearchParams() {
		
	}
	
	public BookSearchParams(String title, String author) {
		this.title = title;
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
	
}
