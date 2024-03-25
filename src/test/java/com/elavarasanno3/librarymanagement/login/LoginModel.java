package com.elavarasanno3.librarymanagement.login;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;

class LoginModel {

	private LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void validateUser(String userName, String password) {
		if (isValidUserName(userName)) {
			if (isValidPassword(userName,password)) {
				loginView.onSuccess();
			} else {
				loginView.onLoginFailed("Invalid password");
			}
		} else {
			loginView.onLoginFailed("Invalid User Name");
		}
	}

	// this method should be private because this method used only with in this
	// class.
	private boolean isValidUserName(String userName) {
		return userName.equals(LibraryDatabase.getInstance().admin.getName());
	}

	// this method should be private because this method used only with in this
	// class.
	private boolean isValidPassword(String userName, String password) {
		return (userName.equals(LibraryDatabase.getInstance().admin.getName())&&password.equals(LibraryDatabase.getInstance().admin.getPassword()));
	}
}
