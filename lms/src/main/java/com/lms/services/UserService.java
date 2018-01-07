package com.lms.services;

import java.util.List;

import com.lms.domain.User;

public interface UserService {

	public void addUser(User user);
	
	public List<User> searchUsers(String name);
	
	public boolean exists(User user);
}
