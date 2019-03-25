package com.example.mobilebasico.ui.register;

import android.view.View;

import com.example.mobilebasico.model.Users;

import java.sql.SQLException;

public interface RegisterContract {

    interface View{

        void onMessage(String message);

    }

    interface Presenter{

        void checkValues(String userName, String userEmail, String userPassword) throws SQLException;

        boolean checkUserExist(String userEmail) throws SQLException;

        void addUser(Users user) throws SQLException;

    }

}
