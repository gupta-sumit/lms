package com.lms.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAccount {

	private final User user;
	private List<Book> borrowedBooks;
	
	public UserAccount(User user) {
		this.user = user;
		this.borrowedBooks = new ArrayList<>();
	}
	
	public void addBook(Book book) {
		this.borrowedBooks.add(book);
	}
	
	public List<Book> getBooks() {
		return Collections.unmodifiableList(borrowedBooks);
	}
	
	public void removeBook(Book book) {
		this.borrowedBooks.remove(book);
	}
	
	public User getUser() {
		return user;
	}
}
