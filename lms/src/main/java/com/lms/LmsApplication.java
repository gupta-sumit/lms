package com.lms;

import java.util.List;

import org.springframework.util.Assert;

import com.lms.domain.Book;
import com.lms.domain.BookBorrowRequest;
import com.lms.domain.BookBorrowResponse;
import com.lms.domain.BookSearchParams;
import com.lms.domain.User;
import com.lms.domain.BookBorrowResponse.BorrowStatus;
import com.lms.services.BooksManager;
import com.lms.services.UserBookAccountManager;
import com.lms.services.UserService;
import com.lms.services.impl.BooksManagerImpl;
import com.lms.services.impl.UserBookAccountManagerImpl;
import com.lms.services.impl.UserServiceImpl;
import com.lms.store.BookStore;
import com.lms.store.impl.BookStoreImpl;

public class LmsApplication {

	public static void main(String[] args) {
		BookStore bookStore = new BookStoreImpl();
		UserBookAccountManager userBookAccountManager = new UserBookAccountManagerImpl();
		UserService userService = new UserServiceImpl();
		userService.addUser(new User("sgu180", "Sumit Gupta"));
		userService.addUser(new User("pram", "Prasad Ram"));
		userService.addUser(new User("hello123", "Hello"));
		userService.addUser(new User("ramp", "Ram Prasad"));
		List<User> searchUsers = userService.searchUsers("Ram");
		org.springframework.util.Assert.isTrue(searchUsers.size() == 2, "Must be 2");
		BooksManager booksManager = new BooksManagerImpl(bookStore, userBookAccountManager, userService);
		booksManager.setBookBorrowLimit(1);
		booksManager.addBook(new Book("100", "Wellness Sense", "Om Swami"), 5);
		booksManager.addBook(new Book("101", "Hit Refresh", "Satya Nadella"), 5);
		booksManager.addBook(new Book("102", "The Journey Home", "Radhanath Swami"), 5);
		List<Book> books = booksManager.searchBooks(new BookSearchParams("Hit Refresh", "Satya Nadella"));
		org.springframework.util.Assert.isTrue(books.size() == 1, "Must be 1");
		BookBorrowResponse borrowBookResponse = booksManager.borrowBook(new BookBorrowRequest(books.get(0), new User("sgu180", "Sumit Gupta")));
		Assert.isTrue(borrowBookResponse.getStatus() == BorrowStatus.GRANTED, "granted");
		books = booksManager.searchBooks(new BookSearchParams("Wellness Sense", "Om Swami"));
		borrowBookResponse = booksManager.borrowBook(new BookBorrowRequest(books.get(0), new User("sgu180", "Sumit Gupta")));
		Assert.isTrue(borrowBookResponse.getStatus() == BorrowStatus.BORROW_LIMIT_EXCEEDED, "limit exceeded");
		booksManager.returnBook(new Book("101", "Hit Refresh", "Satya Nadella"), new User("sgu180", "Sumit Gupta"));
		Assert.isTrue(bookStore.getAvailableCount(books.get(0)) == 5, "5 books should be available");
		Assert.isTrue(userBookAccountManager.getBorrowedBookByUser(new User("sgu180", "Sumit Gupta")).size() == 0, "0 books should be available");
	}
}
