package com.lms.domain;

public class BookInventory {
	
	private Book book;
	private int availableBooks;
	
	public BookInventory(Book book2, int availableCount) {
		this.book = book2;
		this.availableBooks = availableCount;
	}
	
	
	public Book getBook() {
		return this.book;
	}
	
	public int getAvailableBooks() {
		return availableBooks;
	}
	
	public void increaseBooks(int count) {
		this.availableBooks = this.availableBooks  + count;
	}
	
}
