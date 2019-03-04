package com.example.mobilebasico.ui.register;

import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Users;

import java.sql.SQLException;

public class RegisterPresenter <V extends RegisterContract.View> implements RegisterContract.Presenter<V>{
    //Classe Presenter extende Contrato da View e implementa o Contrato do Presenter

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    private RegisterContract.View view;
    private AppDbHelper appDbHelper;

    public RegisterPresenter(RegisterContract.View view){
        this.view = view;
    }

    @Override
    public void checkValues(String userName, String userEmail, String userPassword) throws SQLException {
        //Valida se os campos foram preenchidos
        if ( !userName.isEmpty() ){
            if ( !userEmail.isEmpty() ) {
                if ( !userPassword.isEmpty() ){

                    //Cria o objeto Users
                    Users users = new Users();
                    users.setName(userName);
                    users.setEmail(userEmail);
                    users.setPassword(userPassword);
                    Log.i(TAG, "Usuario: " + users);

                    //Verifica se o nome do usuario já existe
                    if ( checkUserExist(userName) ) {
                        view.onError("Este usuário já existe.");
                        Log.i(TAG, "Já existe Usuario: " + userName);
                    }else{
                        //Adiciona o usuario no banco de dados
                        addUser(users);
                        Log.i(TAG, "Add Usuario: " + users);
                    }


                }else{
                    view.onError("Informe a senha.");
                }
            }else{
                view.onError("Informe o e-mail.");
            }
        }else{
            view.onError("Informe o usuário.");
        }

    }

    @Override
    public boolean checkUserExist(String userName) {
        return false;
    }

    @Override
    public void addUser(Users user) throws SQLException {
        appDbHelper.insertUser(user);
    }
}
