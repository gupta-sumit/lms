package com.lms.services;

import java.util.List;

import com.lms.domain.Book;
import com.lms.domain.BookBorrowRequest;
import com.lms.domain.BookBorrowResponse;
import com.lms.domain.BookSearchParams;
import com.lms.domain.User;

public interface BooksManager {

	public void addBook(Book book, int availableNoOfBooks);
	
	public List<Book> searchBooks(BookSearchParams searchParams);
	
	public BookBorrowResponse borrowBook(BookBorrowRequest request);
	
	public void returnBook(Book book, User user);
	
	public void setBookBorrowLimit(int bookBorrowLimit);
	
}
