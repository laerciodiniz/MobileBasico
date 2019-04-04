package com.example.mobilebasico.ui.login;

import android.content.Intent;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Users;
import com.example.mobilebasico.ui.main.MainActivity;
import com.example.mobilebasico.utils.AppConstants;

import java.sql.SQLException;
import java.util.List;

public class LoginPresenter implements LoginContract.Presenter{

    private static final String TAG = LoginPresenter.class.getSimpleName();

    LoginActivity view;
    AppDbHelper appDbHelper;

    public LoginPresenter(LoginActivity view, AppDbHelper appDbHelper) {
        this.view = view;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public Users getCurrentUser(String userEmail) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        Users user = helper.queryUser(userEmail);
        return user;
    }

    @Override
    public void checkLogin(String userEmail, String userPassword) throws SQLException {

        if ( !userEmail.isEmpty() || !userPassword.isEmpty() ) {
            if ( validateLogin(userEmail, userPassword) ){

                Users user = getCurrentUser(userEmail);

                Intent intent = new Intent(view.getApplicationContext(), MainActivity.class);
                intent.putExtra(AppConstants.BUNDLE_USER_ID, user.getId());
                intent.putExtra(AppConstants.BUNDLE_USER_NAME, user.getName());
                intent.putExtra(AppConstants.BUNDLE_USER_MAIL, userEmail);
                view.startActivity(intent);

            }else{
                view.onError("E-mail ou senha inválido.");
            }
        }else{
            view.onError("Preencha e-mail e senha.");
        }
    }

    @Override
    public boolean validateLogin(String userEmail, String userPassword) throws SQLException {

        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        List<Users> result = helper.queryValidateLogin(userEmail, userPassword);

        if ( result.size() > 0 ){
            return true;
        }else{
            view.onError("E-mail ou senha inválidos.");
            return false;
        }
    }



}
