package com.elavarasanno3.librarymanagement.manageusers;

import java.util.Scanner;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.Credentials;
import com.elavarasanno3.librarymanagement.model.User;

import javax.jws.soap.SOAPBinding;

public class ManageUsersView {
	private ManageUsersModel manageUsersModel;
	public ManageUsersView(){
		manageUsersModel = new ManageUsersModel(this);
	}
	Scanner scanner = new Scanner(System.in);
	public void initAdd() {
		System.out.print(" --> Enter the following User Details: ");
		User user = new User();
		System.out.print("\nEnter User name : ");
		String name = scanner.nextLine();
		user.setName(name);
		System.out.print("\nEnter User emailId : ");
		String gmail = scanner.nextLine();
		user.setEmailId(gmail);
		System.out.print("\nEnter new password : ");
		user.setPassword(scanner.nextLine());
		if(manageUsersModel.nameAndEmailVerification(name,gmail)){
			manageUsersModel.addNewUser(user);
		}else{
			initAdd();
		}
	}
	public void onUserAdded(User user){
		System.out.println("\n------- User'" + user.getName() + "' added successfully ------- \n");
		checkForAddNewUser();
	}
	public void onUserExist(User user) {
		System.out.println("\n------- User '" + user.getName() + "' already exist -------\n");
		checkForAddNewUser();
	}
	public void alertMessage(String text){
		System.out.println(text);
		System.out.println("------------------------------------------------------------");
	}
	private void checkForAddNewUser() {
		System.out.println("Do you want to add more User? \nType Yes/No");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.next().toLowerCase();
		if (choice.equals("yes")) {
			initAdd();
		} else if (choice.equals("no")) {
			System.out.println("\n Thanks for adding users");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}


	public void updateUser(int id) {
		User user = manageUsersModel.updateUser(id);
		if(user != null){
			System.out.print("\n 1 --> change user name\n 2 --> change user gmail\n 3 --> change user password\n 0 --> Exit");
			System.out.print("\nEnter your choice : ");
			String choice = scanner.nextLine();
			while(true){
				switch (choice){
					case "1" :
						System.out.print("Enter new User name : ");
						String username = scanner.nextLine();;
						manageUsersModel.changeUserName(username,user);
						return;
					case "2" :
						System.out.print("\nEnter new User gmail : ");
						String gmail = scanner.nextLine();
						manageUsersModel.changeUserGmail(gmail,user);
						return;
					case "3" :
						System.out.print("\nEnter new User password : ");
						String password = scanner.nextLine();
						manageUsersModel.changeUserPassword(password,user);
						return;
					case "0" :
						return;
					default:
						System.out.println("-- Enter valid choice --");
						updateUser(id);
						break;

				}
			}

		}else{
			System.out.println("Enter a valid user id");
			return;
		}
	}
}