package com.elavarasanno3.librarymanagement.model;

import java.util.HashMap;

public class Credentials {
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	int bookId = 1 ;
	int userId  = 1;
	public Credentials(){
	}
}
