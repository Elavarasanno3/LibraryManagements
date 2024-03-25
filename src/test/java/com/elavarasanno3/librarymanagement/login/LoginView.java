package com.elavarasanno3.librarymanagement.login;

import java.util.Scanner;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.librarysetup.LibrarySetupView;
import com.elavarasanno3.librarymanagement.LibraryManagement2024;

public class LoginView {

	private LoginModel loginModel;

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void init() {
		System.out.println("-----"+ LibraryManagement2024.getInstance().getAppName() +"----- \n    ----- version "
				+ LibraryManagement2024.getInstance().getAppVersion()+" -----");
		System.out.println("\n----- Admin Portal login to proceed-----");
		proceedLogin();
	}

	public void onSuccess() {
		System.out.flush();
		System.out.println(
				"\nLogin successful...\n ----- welcome to " + LibraryManagement2024.getInstance().getAppName()
						+ "- V - " + LibraryManagement2024.getInstance().getAppVersion() + " ----- ");
		LibrarySetupView librarySetupView = new LibrarySetupView();
		librarySetupView.init();
	}

	public void onLoginFailed(String alertText) {
		System.out.println(alertText);
		checkForLogin();
	}

	private void checkForLogin() {
		System.out.println("Do you try again? \nType Yes/No");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			proceedLogin();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n ---- Thanks You ----");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForLogin();
		}
	}

	private void proceedLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n Enter Admin name : ");
		String userName = scanner.nextLine();
		System.out.print("\n Enter password : ");
		String password = scanner.nextLine();
		loginModel.validateUser(userName, password);
	}
}