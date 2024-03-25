package com.elavarasanno3.librarymanagement.bookhome;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.managebook.ManageBookView;
import com.elavarasanno3.librarymanagement.manageusers.ManageUsersView;
import com.elavarasanno3.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookHomeView {
    private BookHomeModel bookHomeModel;
    public BookHomeView(){
        bookHomeModel = new BookHomeModel(this);
    }
    Scanner scanner = new Scanner(System.in);
    public void getBookListDetails(){
        ArrayList<Book> bookList = LibraryDatabase.getInstance().getBookList();
        for(Book book : bookList){
            System.out.print("\n----------Book Details : \n_______________________________________");
            System.out.print("\n"+book.getId() +  " --  Book name : " + book.getName());
            System.out.print("\nAvailable books : " +book.getCount());
            System.out.print("\n --  Book Author : "+ book.getAuthor());
        }
    }
    public void init(){
        while(true) {
            System.out.print("\n 1 --> Add Books \n 2 --> Delete Books\n 3 --> View Books List \n 4 --> " +
                    "Update Book\n 5 --> Search Books\n 0 --> Exit\n");
            System.out.print("\nEnter your choice : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    new ManageBookView().initAdd();
                    break;
                case "2":
                    getBookListDetails();
                    System.out.print("\nEnter Book id to delete : ");
                    int bookId = scanner.nextInt();
                    new ManageBookView().deleteBook(bookId);
                    break;
                case "3":
                    getBookListDetails();
                    // need to work
                    break;
                case "4":
                    getBookListDetails();
                    System.out.print("\nEnter the id to update : ");
                    int updateId = scanner.nextInt();
                    new ManageBookView().updateBook(updateId);
                    break;
                    //need to work
                case "5":
                    System.out.print("\nEnter name to search : ");
                    String searchId = scanner.nextLine();
                    new ManageBookView().searchBook(searchId);
                    break;
                case "0":
                    System.out.println("----Thanks You ----");
                    return;
                default:
                    System.out.println("--You entered an invalid choice--");
                    init();
                    return;
            }
        }
    }
}
