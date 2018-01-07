package com.lms.domain;

import org.apache.commons.lang3.StringUtils;

public class Book {

	private final String isbn;
	private final String title;
	private final String author;
	private int publishedYear;
	
	public Book(String isbn, String title, String author) {
		if(StringUtils.isBlank(isbn)) {
			throw new IllegalArgumentException("isbn must not be blank");
		}
		if(StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("isbn must not be blank");
		}
		if(StringUtils.isBlank(author)) {
			throw new IllegalArgumentException("isbn must not be blank");
		}
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public int getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publishedYear=" + publishedYear
				+ "]";
	}
	
	
	
	
}
