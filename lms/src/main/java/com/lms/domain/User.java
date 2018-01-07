package com.lms.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class User {

	private final String userId;
	private final String userName;
	
	public User(String userId, String userName) {
		super();
		Assert.notNull(userId, "userId must not be null");
		Assert.notNull(userName, "userName must not be null");
		if(StringUtils.isBlank(userId)) {
			throw new IllegalArgumentException("userId must not be blank");
		}
		if(StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("username must not be blank");
		}
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
	
	
	
}
