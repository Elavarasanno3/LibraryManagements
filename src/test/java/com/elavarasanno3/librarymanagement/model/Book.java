package com.elavarasanno3.librarymanagement.model;

public class Book {

	private String name;
	private int id;
	private int count = 0;
	private String author;
	private String publication;
	private String edition;
	private String journer;
	private int volume;

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
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getJourner() {
		return journer;
	}

	public void setJourner(String journer) {
		this.journer = journer;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
