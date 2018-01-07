package com.lms.domain;

public class BookBorrowResponse {

	private final Book book;
	private final BorrowStatus status;
	
	public BookBorrowResponse(Book book, BorrowStatus status) {
		super();
		this.book = book;
		this.status = status;
	}

	public enum BorrowStatus {
		GRANTED,
		BORROW_LIMIT_EXCEEDED,
		BOOK_NOT_AVAILABLE
	}

	public Book getBook() {
		return book;
	}

	public BorrowStatus getStatus() {
		return status;
	}
	
	
}
