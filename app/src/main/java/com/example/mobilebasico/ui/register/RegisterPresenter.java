package com.example.mobilebasico.ui.register;

import android.content.Context;
import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.database.DbOpenHelper;
import com.example.mobilebasico.model.Users;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter{
    //Classe Presenter extende Contrato da View e implementa o Contrato do Presenter

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    RegisterActivity view;
    AppDbHelper appDbHelper;

    public RegisterPresenter(RegisterActivity view, AppDbHelper appDbHelper){
        this.view = view;
        this.appDbHelper = appDbHelper;
    }

    public RegisterPresenter() {
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
    public boolean checkUserExist(String userName) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        List<Users> result = helper.queryUserName(userName);

        if ( result.size() == 0){
            Log.i(TAG,"Não existe: "+ userName );
            return false;
        }else{
            Log.i(TAG,"Já existe: "+ userName + " = " + result.size());
            return true;
        }
    }

    @Override
    public void addUser(Users user) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        helper.insertUser(user);
    }
}
