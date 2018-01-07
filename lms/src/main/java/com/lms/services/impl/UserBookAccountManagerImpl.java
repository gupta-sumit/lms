package com.lms.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.Assert;

import com.lms.domain.Book;
import com.lms.domain.User;
import com.lms.domain.UserAccount;
import com.lms.services.UserBookAccountManager;

public class UserBookAccountManagerImpl implements UserBookAccountManager {

	private Map<User, UserAccount> userAccountMap = new ConcurrentHashMap<>();
	
	@Override
	public List<Book> getBorrowedBookByUser(User user) {
		Assert.notNull(user, "user must not be null");
		return Optional.ofNullable(userAccountMap.get(user)).map(u -> u.getBooks()).orElse(Collections.emptyList());
	}

	@Override
	public void addBookToUserAccount(Book book, User user) {
		Assert.notNull(user, "user must not be null");
		Assert.notNull(book, "book must not be null");
		UserAccount userAccount = Optional.ofNullable(userAccountMap.get(user)).orElse(new UserAccount(user));
		userAccount.addBook(book);
		userAccountMap.put(user, userAccount);
	}

	@Override
	public boolean removeBookFromUserAccount(Book book, User user) {
		Assert.notNull(user, "user must not be null");
		Assert.notNull(book, "book must not be null");
		if(userAccountMap.containsKey(user) && userAccountMap.get(user).getBooks().contains(book)) {
			userAccountMap.get(user).removeBook(book);
			if(org.springframework.util.CollectionUtils.isEmpty(userAccountMap.get(user).getBooks())) {
				// remove User if required
				userAccountMap.remove(user);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean haveBorrowedBookEarlier(Book book, User user) {
		return userAccountMap.containsKey(user) && userAccountMap.get(user).getBooks().contains(book);
	}
	
	

}
