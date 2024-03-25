package com.elavarasanno3.librarymanagement.userlogin;

import com.elavarasanno3.librarymanagement.bookselection.BookSelectionView;
import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.managebook.ManageBookView;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserLoginView {
    Scanner in = new Scanner(System.in);
    String userName;
    String userPassword;
    Scanner scanner = new Scanner(System.in);
    private UserLoginModel userLoginModel;
    public UserLoginView(){
        userLoginModel = new UserLoginModel(this);
    }
    public void proceedLogin(){
        System.out.print("\n----- Hii !! This is user's portal -----");
        System.out.print("\nEnter your name : ");
        userName = in.next();
        System.out.print("\nEnter your password to login : ");
        userPassword = in.next();
        userLoginModel.validateUser(userName,userPassword);
    }
    public void onLoginField(String alertMessage){
        System.out.println(alertMessage);
        checkForLogin();
    }
    private void checkForLogin(){
        System.out.println("Do you want to try again ?\nType YES or NO");
        String choice = in.next().toLowerCase();
        if(choice.equals("yes")){
            proceedLogin();
        }else if(choice.equals("no")) {
            System.out.println("---- Thanks you ----");
        }else{
            System.out.println("Invalid Choice Enter \n Enter a valid choice");
            checkForLogin();
        }
    }
    public void init(){
        User user = userLoginModel.getUser(userName,userPassword);
        while(true){
            System.out.println(" 1 --> Get Book\n 2 --> Borrowed Book Details\n 3 --> Return Book\n 4 --> Library Book details\n 0 --> Exit\n");
            System.out.println("Enter your choice : ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1" :
                    System.out.println("Start selecting book that you want :");
                    new BookSelectionView().init(user);
                    System.out.println();
                    break;
                case "2" :
                    borrowedBookView(user);
                    break;
                case "3" :
                    borrowedBookView(user);
                    System.out.print("\nEnter book id to return : ");
                    int id = scanner.nextInt();
                    ManageBookView manageBookView = new ManageBookView();
                    manageBookView.borrowingBook(user,id);
                    break;
                case "4" :
                    getBookListDetails();
                    break;
                case "0":
                    return;
            }
        }
    }
    public void getBookListDetails(){
        ArrayList<Book> bookList = LibraryDatabase.getInstance().getBookList();
        for(Book book : bookList){
            System.out.print("\n----------Book Details : \n_______________________________________");
            System.out.print("\n"+book.getId() +  " --  Book name : " + book.getName());
            System.out.print("\nAvailable books : " +book.getCount());
            System.out.print("\n --  Book Author : "+ book.getAuthor());
        }
    }
    public  void borrowedBookView(User user){

            System.out.println("--------- Borrowed Books --------");
            for(Book book : user.getUserBookList()){
                System.out.print(book.getId()+ " --> " + " \n ---> Book name : " + book.getName());
                System.out.print("\n    ---> Book Author : " + book.getAuthor());
                System.out.print("\n    ---> Book count : " + book.getCount()+"\n");
                System.out.println("--------------------------------------------------------");

            }
    }
    public void loginSuccess() {
        System.out.println("User login successful");
        init();
    }
}
