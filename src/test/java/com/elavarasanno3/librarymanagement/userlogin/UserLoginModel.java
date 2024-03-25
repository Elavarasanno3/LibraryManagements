package com.elavarasanno3.librarymanagement.userlogin;

import com.elavarasanno3.librarymanagement.datalayer.LibraryDatabase;
import com.elavarasanno3.librarymanagement.model.Book;
import com.elavarasanno3.librarymanagement.model.User;

import java.util.ArrayList;

public class UserLoginModel {
    private UserLoginView userLoginView;
    UserLoginModel(UserLoginView userLoginView){
        this.userLoginView = userLoginView;
    }
    public void validateUser(String userName, String password) {
        if(isValidUserName(userName)){
            if(isValidPassword(password)){
                userLoginView.loginSuccess();
            }else{
                userLoginView.onLoginField("Invalid  Password");
            }
        }else{
            userLoginView.onLoginField("Invalid Username");
        }
    }
    private boolean isValidUserName(String userName)     {
        ArrayList<User> userList =  LibraryDatabase.getInstance().getUserList();
        for(User user:userList){
            if(user.getName().equals(userName)){
                return true;
            }
        }
        return false;
    }


    private boolean isValidPassword(String password) {
        ArrayList <User> userList =  LibraryDatabase.getInstance().getUserList();
        for(User user:userList){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    public User getUser(String userName,String password) {
        for(User user : LibraryDatabase.getInstance().getUserList()){
            if(user.getName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
