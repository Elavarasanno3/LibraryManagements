package com.elavarasanno3.librarymanagement.adminhome;
import java.util.Scanner;
public class AdminHomeView {
    private AdminHomeModel adminHomeModel;
    Scanner scanner = new Scanner(System.in);
    public AdminHomeView(){
        adminHomeModel = new AdminHomeModel(this);
    }

    public void init(){
        while(true){
            System.out.println("-----Admin Manage Portal-----");
            System.out.print("\n 1 --> Change Admin Name\n 2 --> Change Admin Password\n 0 --> Exit");
            System.out.print("\nEnter your choice : ");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    System.out.print("\nEnter new userName : ");
                    String newName = scanner.nextLine();
                    adminHomeModel.changeAdminName(newName);
                    break;
                case "2":
                    System.out.print("\nEnter new password : ");
                    String password = scanner.nextLine();
                    adminHomeModel.changeAdminPassword(password);
                    break;
                case "0":
                    return;
                default:
                    System.out.print("\nEnter valid choice : ");
                    init();
                    break;
            }
        }
    }

}
