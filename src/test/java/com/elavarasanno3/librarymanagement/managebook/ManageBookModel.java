package com.elavarasanno3.librarymanagement.managebook;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.User;

class ManageBookModel {

	private ManageBookView manageBookView;

	ManageBookModel(ManageBookView manageBookView) {
		this.manageBookView = manageBookView;
	}

	public void addNewBook(Book book) {
		if (LibraryDatabase.getInstance().insertBook(book)) {
			manageBookView.onBookAdded(book);
		} else {
			manageBookView.onBookExist(book);
		}
	}
	public boolean nameAndEmailVerification(String name,String author){
		if(nameVerification(name)){
			if(nameVerification(author)){
				return true;
			}else{
				manageBookView.alertMessage("\n -----Alert : Enter valid Book name & Author-----");
				return false;
			}
		}else{
			manageBookView.alertMessage("\n -----Alert : Enter valid Book name-----");
			return false;
		}

	}
	public boolean nameVerification(String name){
		return name.length() >= 3 && name.length() <= 50;
	}

    public void deleteBook(int bookId) {
        LibraryDatabase.getInstance().getBookList().removeIf(book -> book.getId() == bookId);
		LibraryDatabase.getInstance().updateBook();
    }

	public Book updateBook(int id) {
		for(Book book : LibraryDatabase.getInstance().getBookList()){
			if(book.getId() == id){
				return book;
			}
		}
		return null;
	}

	public void changeBookName(String bookName, Book book) {
		book.setName(bookName);
		LibraryDatabase.getInstance().updateBook();
		System.out.println("---name changed successfully---");
	}

	public void changeBookAuthor(String author, Book book) {
		book.setAuthor(author);
		LibraryDatabase.getInstance().updateBook();
		System.out.println("---author name changed successfully---");
	}

	public void changeBookCount(int count, Book book) {
		book.setCount(count);
		LibraryDatabase.getInstance().updateBook();
		System.out.println("---book count changed successfully---");
	}
}
