package com.lms.store;

import java.util.List;

import com.lms.domain.Book;
import com.lms.domain.BookSearchParams;

public interface BookStore {

	public List<Book> searchBooks(BookSearchParams bookSearchParams);
	
	public void addBook(Book book, int availableCount);
	
	public int getAvailableCount(Book book);
	
	public void increaseInventory(Book book, int books);
	
	public void reduceBookInventory(Book book, int books);
	
}
