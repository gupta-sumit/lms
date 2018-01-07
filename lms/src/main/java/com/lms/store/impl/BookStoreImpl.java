package com.lms.store.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.lms.domain.Book;
import com.lms.domain.BookInventory;
import com.lms.domain.BookSearchParams;
import com.lms.store.BookStore;

public class BookStoreImpl implements BookStore {

	private Map<Book, BookInventory> booksInfoMap = new ConcurrentHashMap<>();
	
	@Override
	public List<Book> searchBooks(BookSearchParams bookSearchParams) {
		return booksInfoMap.keySet().stream().filter(book -> {
			return book.getAuthor().equalsIgnoreCase(bookSearchParams.getAuthor()) && book.getTitle().equalsIgnoreCase(bookSearchParams.getTitle());
		}).collect(Collectors.toList());
	}

	@Override
	public void addBook(Book book, int availableCount) {
		Assert.notNull(book, "book must not be null");
		if(availableCount <= 0) {
			throw new IllegalArgumentException("availableCount must be > 0");
		}
		booksInfoMap.put(book, new BookInventory(book, availableCount));
	}

	@Override
	public int getAvailableCount(Book book) {
		if(booksInfoMap.containsKey(book)) {
			return booksInfoMap.get(book).getAvailableBooks();
		}
		return 0;
	}

	@Override
	public void increaseInventory(Book book, int books) {
		if(booksInfoMap.containsKey(book)) {
			booksInfoMap.get(book).increaseBooks(books);
		} else {
			throw new RuntimeException("book does not exists " + book.getTitle());
		}
		

	}

	@Override
	public void reduceBookInventory(Book book, int books) {
		if(booksInfoMap.containsKey(book)) {
			booksInfoMap.get(book).increaseBooks(-books);
		} else {
			throw new RuntimeException("book does not exists " + book.getTitle());
		}
		
		
	}

}
