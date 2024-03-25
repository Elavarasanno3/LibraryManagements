package com.elavarasanno3.librarymanagement.datalayer;
import java.io.File;
import java.io.IOException;

import com.elavarasanno3.librarymanagement.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;

public class LibraryDatabase {
	private static LibraryDatabase libraryDatabase;
	ObjectMapper mapper = new ObjectMapper();
	private ArrayList<Book> bookList = new ArrayList<>();
	private ArrayList <User> userList = new ArrayList<>();
//	private ArrayList <Book> usersBookList = new ArrayList<>();

	public Credentials credentials = new Credentials();
	public Library library = new Library();
	public Admin admin = new Admin();


	public void setLibrary(Library library) {
		this.library = library;
	}
	public Library getLibrary(){
		return library;
	}

	private String readFile(String filePath) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();  // To store file contents

		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append("\n");  // Add newline for each line
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found: " + filePath);
		}

		return sb.toString();
	}
	public void initialUsers(){
		File jsonFile = new File("src/test/java/com/elavarasanno3/librarymanagement/data/user.json");
		try {
			if (jsonFile.length() == 0) {
				return;
			}
			userList = mapper.readValue(jsonFile, new TypeReference<ArrayList<User>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void initialBooks(){
		File jsonFile = new File("src/test/java/com/elavarasanno3/librarymanagement/data/book.json");
		try {
			if (jsonFile.length() == 0) {
				return;
			}
			bookList = mapper.readValue(jsonFile, new TypeReference<ArrayList<Book>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void initialBooks(){
//		String jsonString = null;
//		try {
//			jsonString = readFile("C:\\Users\\el\\Desktop\\ZOHO1\\PROJECTS\\LibraryManagement2\\src\\test\\java\\com\\elavarasanno3\\librarymanagement\\data\\book.json");
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//		TypeReference<ArrayList<Book>> typeReference = new TypeReference<ArrayList<Book>>() {};
//		if(!(jsonString.isEmpty())){
//			try {
//				bookList = (ArrayList<Book>) mapper.readValue(jsonString, typeReference);
//			} catch (JsonProcessingException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}

	public void initialCredentials(){
		String jsonString = null;
		try {
			jsonString = readFile("src/test/java/com/elavarasanno3/librarymanagement/data/credentials.json");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(!(jsonString.isEmpty())){
			try {
				credentials = mapper.readValue(jsonString, Credentials.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public void initialAdmin(){
		String jsonString = null;
		try {
			jsonString = readFile("src/test/java/com/elavarasanno3/librarymanagement/data/admin.json");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(!(jsonString.isEmpty())){
			try {
				admin = mapper.readValue(jsonString, Admin.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public void initialLibrary(){
		String jsonString = null;
		try {
			jsonString = readFile("src/test/java/com/elavarasanno3/librarymanagement/data/library.json");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(!(jsonString.isEmpty())){
			try {
				library = mapper.readValue(jsonString, Library.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public ArrayList<User> getUserList(){
		return userList;
	}
	public static LibraryDatabase getInstance(){
		if(libraryDatabase == null){
			libraryDatabase = new LibraryDatabase();
		}
		return libraryDatabase;
	}

	public void removeUser(int id){
		if(!userList.isEmpty()){
			System.out.println("User " + userList.get(id-1).getName()+" removed Successfully");
			userList.remove(id-1);
			try {
				mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/user.json"), userList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("User list is Empty.");
		}
	}
	public ArrayList<Book> getBookList(){
		return bookList;
	}
	public void removeBook(int id){
		if(!bookList.isEmpty()){
			System.out.println("Book " + bookList.get(id-1).getName()+" removed Successfully");
			bookList.remove(id-1);
				try {
					mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/book.json"), bookList);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			System.out.println("Book list is empty.");
		}

	}





//	public Library insertLibrary(Library library2){
//		this.library = library2;
//		this.library.setLibraryId(1);
//		return library;
//	}
	public boolean insertBook(Book book) {
		boolean hasBook = false;
		for (Book addedBook : bookList) {
			if (addedBook.getAuthor().equals(book.getAuthor())) {
				hasBook = true;
				break;
			}
		}
		if (hasBook) {
			return false;
		} else {
			bookList.add(book);
			try {
				mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/book.json"), bookList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("JSON user json created successfully: ");
			
			return true;
		}
	}

	public boolean insertUser(User user) {
		boolean hasUser = false;
		for (User addedUser : userList) {
			if (addedUser.getEmailId().equals(user.getEmailId())) {
				hasUser = true;
				break;
			}
		}
		if (hasUser) {
			return false;
		} else {
			userList.add(user);
			user.setId(credentials.getUserId());
			credentials.setUserId(credentials.getUserId()+1);
			LibraryDatabase.getInstance().updateCredentials(credentials);
			try {
				mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/user.json"), userList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("JSON user json created successfully: ");
			return true;
		}
	}


	public void updateUser(User user) {
		try {
			mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/user.json"), userList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateCredentials(Credentials credentials) {
		try {
			mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/credentials.json"), credentials);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBook() {
		try {
			mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/book.json"), bookList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateLibrary(){
		try {
			mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/library.json"), library);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateAdmin(){
		try {
			mapper.writeValue(new File("src/test/java/com/elavarasanno3/librarymanagement/data/admin.json"), admin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

