package com.elavarasanno3.librarymanagement.mainlogin;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.login.LoginView;
import com.elavarasanno3.librarymanagement.userlogin.UserLoginView;

import java.util.Scanner;

public class MainLoginView {
    private MainLoginModel mainLoginModel;
    Scanner scanner = new Scanner(System.in);
    public MainLoginView(){
        LibraryDatabase.getInstance().initialUsers();
        LibraryDatabase.getInstance().initialBooks();
        LibraryDatabase.getInstance().initialCredentials();
        LibraryDatabase.getInstance().initialAdmin();
        LibraryDatabase.getInstance().initialLibrary();

        mainLoginModel = new MainLoginModel(this);
    }
    public void init(){
        while(true){
            System.out.println("\n 1 --> ADMIN LOGIN \n 2 --> USER LOGIN\n 0 --> EXIT ");
            System.out.print("\nEnter your choice : ");
            String choice = scanner.next();
            switch (choice){
                case "1":
                    new LoginView().init();
                    break;
                case "2":
                    if(!(LibraryDatabase.getInstance().getUserList().isEmpty())){
                        new UserLoginView().proceedLogin();
                    }else{
                        System.out.println("No users added.... first add users");
                        init();
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Enter valid choice : ");
                    init();
            }
        }

    }
}
