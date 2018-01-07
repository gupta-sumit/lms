package com.lms.services.impl;

import java.util.List;

import com.lms.domain.Book;
import com.lms.domain.BookBorrowRequest;
import com.lms.domain.BookBorrowResponse;
import com.lms.domain.BookBorrowResponse.BorrowStatus;
import com.lms.domain.BookSearchParams;
import com.lms.domain.User;
import com.lms.services.BooksManager;
import com.lms.services.UserBookAccountManager;
import com.lms.services.UserService;
import com.lms.store.BookStore;

public class BooksManagerImpl implements BooksManager {

	private final BookStore bookStore;
	private int bookLimitPerUser = 5;
	private UserBookAccountManager userBookAccountManager;
	private final UserService userService;

	public BooksManagerImpl(BookStore bookStore, UserBookAccountManager accountManager, UserService userService) {
		super();
		this.bookStore = bookStore;
		userBookAccountManager = accountManager;
		this.userService = userService;
	}

	@Override
	public void addBook(Book book, int availableNoOfBooks) {
		bookStore.addBook(book, availableNoOfBooks);
	}

	@Override
	public List<Book> searchBooks(BookSearchParams searchParams) {
		return bookStore.searchBooks(searchParams);
	}

	@Override
	public BookBorrowResponse borrowBook(BookBorrowRequest request) {
		if(!userService.exists(request.getUser())) {
			throw new RuntimeException("user does not exists " + request.getUser().getUserId());
		}
		if(userBookAccountManager.haveBorrowedBookEarlier(request.getBook(), request.getUser())) {
			throw new RuntimeException("User " + request.getUser().getUserId() + " have already borrowed book " + request.getBook().getTitle());
		}
		int availableCount = bookStore.getAvailableCount(request.getBook());
		int userBorrowedBooks = userBookAccountManager.getBorrowedBookByUser(request.getUser()).size();
		if (availableCount > 0) {
			if (userBorrowedBooks < bookLimitPerUser) {
				// grant book
				bookStore.reduceBookInventory(request.getBook(),1);
				userBookAccountManager.addBookToUserAccount(request.getBook(), request.getUser());
				return new BookBorrowResponse(request.getBook(), BorrowStatus.GRANTED);
			} else {
				return new BookBorrowResponse(request.getBook(), BorrowStatus.BORROW_LIMIT_EXCEEDED);
			}
		} else {
			return new BookBorrowResponse(request.getBook(), BorrowStatus.BOOK_NOT_AVAILABLE);
		}
	}

	@Override
	public void returnBook(Book book, User user) {
		userBookAccountManager.removeBookFromUserAccount(book, user);
		bookStore.increaseInventory(book, 1);
	}

	@Override
	public synchronized void setBookBorrowLimit(int bookBorrowLimit) {
		if (bookBorrowLimit <= 0) {
			throw new RuntimeException("borrowLimit must be > 0");
		}
		this.bookLimitPerUser = bookBorrowLimit;
	}

}
