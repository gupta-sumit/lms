package com.lms.domain;

public class BookBorrowRequest {

	private Book book;
	private User user;
	public BookBorrowRequest(Book book2, User user2) {
		this.book = book2;
		this.user = user2;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

