package com.elavarasanno3.librarymanagement.bookselection;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.librarysetup.LibrarySetupView;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.Library;
import com.elavarasanno3.librarymanagement.model.User;
import com.elavarasanno3.librarymanagement.userlogin.UserLoginView;

import java.util.ArrayList;
import java.util.Scanner;

public class BookSelectionView {
    private BookSelectionModel bookSelectionModel;
    Scanner in = new Scanner(System.in);
    public BookSelectionView (){
        bookSelectionModel = new BookSelectionModel(this);
    }

    public void getBookListDetails(){
        for(Book book : LibraryDatabase.getInstance().getBookList()){
            System.out.println(LibraryDatabase.getInstance().getBookList().size());
            System.out.print("\n----------Book Details : \n_______________________________________");
            System.out.print("\n"+book.getId() +  " --  Book name : " + book.getName());
            System.out.print("\nAvailable books : " +book.getCount());
            System.out.print("\n --  Book Author : "+ book.getAuthor());
        }
    }
    public void init(User user){
        getBookListDetails();
        System.out.print("\nEnter the book id to select : ");
        System.out.print("\n-Enter '0' to exit : " );
        int bookId = in.nextInt();
        if(bookId == 0){
            return;
        }
        bookSelectionModel.startBookSelection(bookId,user);
    }
    public void getRegenerate(User user){
        System.out.println("\nEnter 'YES' to add more books 'NO' to exit : ");
        String choice = in.next().toLowerCase();
        if(choice.equals("yes")){
            init(user);
        }else if(choice.equals("no")) {
            new UserLoginView().loginSuccess();
        }
    }

}
