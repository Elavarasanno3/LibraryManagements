package com.elavarasanno3.librarymanagement.bookselection;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.librarysetup.LibrarySetupView;
import com.elavarasanno3.librarymanagement.model.Library;
import com.elavarasanno3.librarymanagement.model.User;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.userlogin.UserLoginView;

import java.util.ArrayList;
import java.util.Scanner;

public class BookSelectionModel {
    Scanner in = new Scanner(System.in);
    private BookSelectionView bookSelectionView;
    BookSelectionModel (BookSelectionView bookSelectionView){
        this.bookSelectionView = bookSelectionView;
    }

    public void startBookSelection(int id,User user) {
        if (LibraryDatabase.getInstance().getBookList().isEmpty()){
            System.out.println("There is no book available");
        }else{
            for(Book book : LibraryDatabase.getInstance().getBookList()){
                if(book.getId() == id){
                    if(book.getCount() > 0){
                        book.setCount(book.getCount()-1);
                        addUserList(book,user);
                        LibraryDatabase.getInstance().updateUser(user);
                        return;
                    }else{
                        System.out.println("No books available");
                    }
                }
            }
        }
    }

    public void addUserList(Book book,User user){
        Book book1 = new Book();
        book1.setCount(1);
        book1.setAuthor(book.getAuthor());
        book1.setName(book.getName());
        book1.setId(book.getId());
        user.getUserBookList().add(book1);
        LibraryDatabase.getInstance().updateUser(user);
        return;
    }
}

