package com.elavarasanno3.librarymanagement.model;
import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class User {

	private String name;
	ObjectMapper mapper = new ObjectMapper();
	private ArrayList<Book> userBookList = new ArrayList<>();
	public void setUserBookList(ArrayList<Book> bookList) {
		userBookList = bookList;
	}
	public void setUsersBookList(Book book){
		Book book1 = book;
		book1.setCount(1);
		userBookList.add(book1);
		LibraryDatabase.getInstance().updateUser(this);
	}
	public ArrayList<Book> getUserBookList() {
		return userBookList;
	}



	private int id;
	private String password;
	private String phoneNo;
	private String emailId;
	private String address;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
