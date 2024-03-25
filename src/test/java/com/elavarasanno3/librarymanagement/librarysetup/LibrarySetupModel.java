package com.elavarasanno3.librarymanagement.librarysetup;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.Library;

//Access modifier for this LibrarySetupModel class should be default. 
//So that outside of the package this class cannot be accessed.
class LibrarySetupModel {

	private LibrarySetupView librarySetupView ;

	private Library library;
	public LibrarySetupModel(LibrarySetupView interviewSetupView){
		this.librarySetupView = interviewSetupView;
		library = LibraryDatabase.getInstance().getLibrary();
	}

	public void startSetUp() {
		if(library.getLibraryName() == null){
			librarySetupView.initialSetup();
		}else{
			librarySetupView.onSetupComplete();
		}
	}

//	public void createLibrary(Library library) {
//		if (library.getLibraryName().length() <3  || library.getLibraryName().length() > 50) {
//			librarySetupView.showAlert("Enter valid name");
//			return;
//		}
//		System.out.print("\n------Library setup completed-----");
//		System.out.print("\n____________________________________________");
//		librarySetupView.onSetupComplete();
//	}


	public boolean nameAndEmailVerification(String name,String gmail){
		if(nameVerification(name)){
			if(gmailVerification(gmail)){
				return true;
			}else{
				librarySetupView.showAlert("\n -----Alert : Enter valid name &  gmail-----\n");
				return false;
			}
		}else{
			librarySetupView.showAlert("\n-----Alert : Enter valid name-----\n");
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


}
