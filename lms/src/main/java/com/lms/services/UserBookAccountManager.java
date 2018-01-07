package com.lms.services;

import java.util.List;

import com.lms.domain.Book;
import com.lms.domain.User;

public interface UserBookAccountManager {

	public List<Book> getBorrowedBookByUser(User user);
	
	public void addBookToUserAccount(Book book, User user);
	
	public boolean removeBookFromUserAccount(Book book, User user);
	
	public boolean haveBorrowedBookEarlier(Book boo, User user);
}
