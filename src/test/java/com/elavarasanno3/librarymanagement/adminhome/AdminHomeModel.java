package com.elavarasanno3.librarymanagement.adminhome;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;

public class AdminHomeModel {
    private AdminHomeView adminHomeView;
    AdminHomeModel(AdminHomeView adminHomeView){
        this.adminHomeView = adminHomeView;
    }

    public void changeAdminName(String newName) {
        LibraryDatabase.getInstance().admin.setName(newName);
        LibraryDatabase.getInstance().updateAdmin();
    }

    public void changeAdminPassword(String password) {
        LibraryDatabase.getInstance().admin.setPassword(password);
        LibraryDatabase.getInstance().updateAdmin();
    }
}
