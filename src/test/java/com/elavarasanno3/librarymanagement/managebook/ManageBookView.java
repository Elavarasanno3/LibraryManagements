package com.elavarasanno3.librarymanagement.managebook;

import java.util.Scanner;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.Credentials;
import com.elavarasanno3.librarymanagement.model.User;

public class ManageBookView {
	private ManageBookModel manageBookModel;
	Scanner scanner = new Scanner(System.in);
	public ManageBookView(){
		manageBookModel = new ManageBookModel(this);
	}
	public void initAdd() {
		System.out.print("\nEnter the following Book Details: ");
		Scanner scanner = new Scanner(System.in);
		Book book = new Book();
		System.out.print("\nEnter Book name : ");
		String name = scanner.nextLine();
		book.setName(name);
		System.out.print("\nEnter Author name : ");
		String author = scanner.nextLine();
		book.setAuthor(author);
		System.out.print("\nEnter the book count : ");
		book.setCount(scanner.nextInt());

		Credentials credentials =  LibraryDatabase.getInstance().credentials;
		book.setId(credentials.getBookId());
		credentials.setBookId(credentials.getBookId()+1);
		LibraryDatabase.getInstance().updateCredentials(credentials);

		if(manageBookModel.nameAndEmailVerification(name,author)){
			manageBookModel.addNewBook(book);
		}else{
			initAdd();
		}
	}
	public void deleteBook(int id){
		manageBookModel.deleteBook(id);
	}
	public void borrowingBook(User user, int id){
        user.getUserBookList().removeIf(book -> book.getId() == id);
		LibraryDatabase.getInstance().updateUser(user);
		addToLibraryBookList(id,user);
	}
	public void addToLibraryBookList(int id,User user){
		boolean flag = false;
		for(Book book : LibraryDatabase.getInstance().getBookList()){
			if(book.getId() == id){
				book.setCount(book.getCount()+1);
				LibraryDatabase.getInstance().updateBook();
				flag = true;
			}
		}
		if(!flag){
			Book addBook = null;
			for(Book book : user.getUserBookList()){
				if(book.getId() == id){
					addBook = book;
				}
			}
			LibraryDatabase.getInstance().getBookList().add(addBook);
			LibraryDatabase.getInstance().updateBook();
		}
	}
	public void alertMessage(String text){
		System.out.println(text);
		System.out.println("---------------------------------------------------");
	}

	public void onBookAdded(Book book){
		System.out.println("\n------- Book '" + book.getName() + "' added successfully ------- \n");
		checkForAddNewBook();
	}
	public void onBookExist(Book book) {
		System.out.println("\n------- Book '" + book.getName() + "' already exist -------\n");
		checkForAddNewBook();
	}
	private void checkForAddNewBook() {
		System.out.print("\nDo you want to add more Books? \n-----Type Yes/No-----");
		String choice = scanner.next().toLowerCase();
		if (choice.equals("yes")) {
			initAdd();
		} else if (choice.equals("no")) {
			System.out.print("\nThanks for adding Books");
		} else {
			System.out.print("\nInvalid choice, Please enter valid choice");
			checkForAddNewBook();
		}
	}

	public void updateBook(int id) {
		Book book = manageBookModel.updateBook(id);
		if(book != null){
			System.out.print("\n 1 --> change book name\n 2 --> change book author\n 3 --> change book count");
			System.out.print("\nEnter your choice : ");
			String choice = scanner.nextLine();
			while(true){
				switch (choice){
					case "1" :
						System.out.print("Enter new Book name : ");
						String bookName = scanner.nextLine();;
						manageBookModel.changeBookName(bookName,book);
						return;
					case "2" :
						System.out.print("\nEnter new Book gmail : ");
						String author = scanner.nextLine();
						manageBookModel.changeBookAuthor(author,book);
						break;
					case "3" :
						System.out.print("\nEnter new Book count : ");
						int count = scanner.nextInt();
						manageBookModel.changeBookCount(count,book);
						break;
					case "0" :
						return;
					default:
						System.out.println("-- Enter valid choice --");
						updateBook(id);
						break;

				}
			}

		}else{
			System.out.println("Enter a valid user id");
			return;
		}
	}

	public void searchBook(String searchId) {
		for (Book book : LibraryDatabase.getInstance().getBookList()){
			if(book.getName().startsWith(searchId)){
				System.out.print("\n----------Book Details : \n_______________________________________");
				System.out.print("\n"+book.getId() +  " --  Book name : " + book.getName());
				System.out.print("\nAvailable books : " +book.getCount());
				System.out.print("\n --  Book Author : "+ book.getAuthor());
			}
		}
	}
}
