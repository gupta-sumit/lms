package com.lms.services.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.lms.domain.User;
import com.lms.services.UserService;

public class UserServiceImpl implements UserService {

	private List<User> users = new CopyOnWriteArrayList<>();
	
	@Override
	public void addUser(User user) {
		Assert.notNull(user, "user must not be null");
		validateUser(user);
		users.add(user);
	}

	private void validateUser(User user) {
		Assert.notNull(user, "user must not be null");
		if(users.contains(user)) {
			throw new RuntimeException("User already exists " + user.getUserId());
		}
	}

	@Override
	public List<User> searchUsers(String name) {
		return users.stream().filter(u -> u.getUserName().contains(name)).collect(Collectors.toList());
	}

	@Override
	public boolean exists(User user) {
		return users.contains(user);
	}

	
}
