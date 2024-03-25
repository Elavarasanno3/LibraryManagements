package com.elavarasanno3.librarymanagement.librarysetup;

import java.util.ArrayList;
import java.util.Scanner;

import com.elavarasanno3.librarymanagement.LibraryManagement2024;
import com.elavarasanno3.librarymanagement.adminhome.AdminHomeView;
import com.elavarasanno3.librarymanagement.bookhome.BookHomeView;
import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.login.LoginView;
import com.elavarasanno3.librarymanagement.managebook.ManageBookView;
import com.elavarasanno3.librarymanagement.manageusers.ManageUsersView;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.Library;
import com.elavarasanno3.librarymanagement.model.User;
import com.elavarasanno3.librarymanagement.userhome.UserHomeView;
import com.elavarasanno3.librarymanagement.userlogin.UserLoginView;

//Access modifier for this LibrarySetupView class should be public. 
//So that outside of the package this class can be accessed and can create instance for this class.
public class LibrarySetupView {

	private LibrarySetupModel librarySetupModel;
	public LibrarySetupView(){
		librarySetupModel = new LibrarySetupModel(this);
	}
	public void init(){
		librarySetupModel.startSetUp();
	}
	public void showAlert(String alert){
		System.out.println(alert);
		initialSetup();
	}

	public void initialSetup() {
		System.out.print("\nEnter Library details : ");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter library name : ");
		String libraryName =  scanner.nextLine();
		LibraryDatabase.getInstance().library.setLibraryName(libraryName);
		System.out.print("\nEnter email id : " );
		String emailId = scanner.nextLine();
		LibraryDatabase.getInstance().library.setEmailId(emailId);
		if(!(librarySetupModel.nameAndEmailVerification(libraryName,emailId))){
			initialSetup();
		}else{
			LibraryDatabase.getInstance().updateLibrary();
			onSetupComplete();
		}


	}

//	public void getUserBookListDetails(){
//		ArrayList<Book> bookList = LibraryDatabase.getInstance().getUserBookList();
//		System.out.println("\n-----------Book Details-------------");
//		for(Book book : bookList){
//			System.out.print("\n"+ book.getId() +  "."+ " Book name : " + book.getName());
//			System.out.print("\nAvailable books : " +book.getCount());
//			System.out.print("\nBook Author : "+ book.getAuthor());
//			System.out.print("\n");
//		}
//	}

	public void onSetupComplete(){
		System.out.print("\nCurrent Library Name : " + LibraryDatabase.getInstance().library.getLibraryName());
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("\n 1 --> Manage Users\n 2 --> Manage Books\n 3 --> Manage Admin\n 0 --> Exit\n Enter your choice : ");
			String choice = in.next();
			switch (choice) {
				case "1":
					new UserHomeView().init();
					break;
				case "2":
					new BookHomeView().init();
					break;
				case "3":
					new AdminHomeView().init();
					break;
				case "0":
					System.out.print("\n\n-- Thanks for using " + LibraryManagement2024.getInstance().getAppName() + "\n");
					return;
				default:
					System.out.print("\n\nPlease Enter valid choice\n");
			}
		}
	}
}
