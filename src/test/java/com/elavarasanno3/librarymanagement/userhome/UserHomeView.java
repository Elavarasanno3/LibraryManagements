package com.elavarasanno3.librarymanagement.userhome;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.manageusers.ManageUsersView;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserHomeView {
    private UserHomeModel userHomeModel;
    Scanner scanner = new Scanner(System.in);
    public UserHomeView(){
        userHomeModel = new UserHomeModel(this);
    }
    public  void getUsersListDetails(){
        ArrayList<User> userList = LibraryDatabase.getInstance().getUserList();
        System.out.print("\n-----User Details-----");
        for(User user : userList){
            System.out.print("\n"+ user.getId() +  " User name : " + user.getName() + " \n   Email id : "+ user.getEmailId()+"\n");
            System.out.print("---User book list---\n");
            ArrayList<Book> userBookList = (ArrayList<Book>) user.getUserBookList();
            for (Book book : userBookList){
                System.out.print("	Book id : " + book.getId());
                System.out.print("\n	Book name : " + book.getName());
                System.out.print("\n	Book Author : " + book.getAuthor()+"\n");
            }
        }
    }

    public void init() {
        System.out.print("\n----Hello this is Manage Users section ----");
        while(true){
            System.out.print("\n 1 --> Add Users \n 2 --> Delete Users\n 3 --> View Users List \n 4 --> " +
                    "Update User\n 0 --> Exit\n");
            System.out.print("\nEnter your choice : ");
            String choice = scanner.next();

            switch (choice){
                case "1":
                    new ManageUsersView().initAdd();
                    break;
                case "2":
                    getUsersListDetails();
                    System.out.print("Enter the User Id to delete : ");
                    int userId = scanner.nextInt();
                    LibraryDatabase.getInstance().removeUser(userId);
                    // need to work
                    break;
                case "3":
                    getUsersListDetails();
                    // need to work
                    break;
                case "4":
                    //need to work
                    getUsersListDetails();
                    System.out.print("\nEnter the id to update : ");
                    int updateId = scanner.nextInt();
                    new ManageUsersView().updateUser(updateId);
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
