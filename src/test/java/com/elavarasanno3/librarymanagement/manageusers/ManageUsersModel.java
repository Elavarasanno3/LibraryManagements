package com.elavarasanno3.librarymanagement.manageusers;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.User;

public class ManageUsersModel {
	private ManageUsersView manageUsersView;
	ManageUsersModel(ManageUsersView manageUsersView){
		this.manageUsersView = manageUsersView;
	}
	public void addNewUser(User user){
		if(LibraryDatabase.getInstance().insertUser(user)){
			manageUsersView.onUserAdded(user);
		}else{
			manageUsersView.onUserExist(user);
		}
	}
	public boolean nameAndEmailVerification(String name,String gmail){
		if(nameVerification(name)){
			if(gmailVerification(gmail)){
				return true;
			}else{
				manageUsersView.alertMessage("\n -----Alert : Enter valid name & gmail-----\n");
				return false;
			}
		}else{
			manageUsersView.alertMessage("\n-----Alert : Enter valid name-----\n");
			return false;
		}

	}
	public boolean nameVerification(String name){
		return name.length() >= 3 && name.length() <= 50;
	}
	public boolean gmailVerification(String gmail){
		if(gmail.length() < 10)
			return false;
		if(gmail.charAt(gmail.length()-11) == '.')
			return false ;
		if(!(gmail.endsWith("@gmail.com")))
			return false;
		for(int i = 0;i<gmail.length()-10;i++){
			char c = gmail.charAt(i);
			if(!(Character.isAlphabetic(c) || c == '.'|| Character.isDigit(c))) {
				return false;
			}
		}
		return true;
	}


	public User updateUser(int id) {
		for(User user : LibraryDatabase.getInstance().getUserList()){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	public void changeUserName(String username,User user) {
		user.setName(username);
		LibraryDatabase.getInstance().updateUser(user);
		System.out.println("---name changed successfully---");
	}

	public void changeUserGmail(String gmail, User user) {
		user.setEmailId(gmail);
		LibraryDatabase.getInstance().updateUser(user);
		System.out.println("---gmail changed successfully---");
	}

	public void changeUserPassword(String password, User user) {
		user.setPassword(password);
		LibraryDatabase.getInstance().updateUser(user);
		System.out.println("---password changed successfully---");
	}
}

