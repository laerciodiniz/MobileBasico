package com.example.mobilebasico.ui.register;

import android.view.View;

import com.example.mobilebasico.model.Users;

import java.sql.SQLException;

public interface RegisterContract {

    interface View{
        void onError(String message);
    }

    //Presenter extende a view
    interface Presenter<V extends View>{

        void checkValues(String userName, String userEmail, String userPassword) throws SQLException;

        boolean checkUserExist(String userName);

        void addUser(Users user) throws SQLException;

    }

}
