package com.example.mobilebasico.ui.login;

import java.sql.SQLException;

public interface LoginContract {

    interface View{

        void onError(String message);

    }

    interface Presenter{

        void checkLogin(String userEmail, String userPassword) throws SQLException;

        boolean validateLogin(String userEmail, String userPassword) throws SQLException;

    }

}
