package com.example.mobilebasico.ui.register;

import android.content.Intent;
import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Users;
import com.example.mobilebasico.ui.main.MainActivity;
import com.example.mobilebasico.utils.AppConstants;

import java.sql.SQLException;
import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter{
    //Classe Presenter implementa o Contrato do Presenter

    private static final String TAG = RegisterPresenter.class.getSimpleName();

    RegisterActivity view;
    AppDbHelper appDbHelper;

    public RegisterPresenter(RegisterActivity view, AppDbHelper appDbHelper){
        this.view = view;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public void checkValues(String userName, String userEmail, String userPassword) throws SQLException {
        //Valida se os campos foram preenchidos
        if ( !userName.isEmpty() ){
            if ( !userEmail.isEmpty() ) {
                if ( !userPassword.isEmpty() ){

                    //Cria o objeto Users
                    Users user = new Users();
                    user.setName(userName);
                    user.setEmail(userEmail);
                    user.setPassword(userPassword);

                    //Verifica se o nome do usuario já existe
                    if ( checkUserExist(userEmail) ) {
                        view.onMessage("Este e-mail já existe.");
                    }else{
                        //Adiciona o usuario no banco de dados
                        addUser(user);
                        view.onMessage("Conta criada com sucesso.");

                        Intent intent = new Intent(view.getApplicationContext(), MainActivity.class);
                        intent.putExtra(AppConstants.BUNDLE_USER_ID, user.getId());
                        intent.putExtra(AppConstants.BUNDLE_USER_NAME, user.getName());
                        intent.putExtra(AppConstants.BUNDLE_USER_MAIL, userEmail);

                        view.finish();
                        view.startActivity(intent);
                    }


                }else{
                    view.onMessage("Informe a senha.");
                }
            }else{
                view.onMessage("Informe o e-mail.");
            }
        }else{
            view.onMessage("Informe o usuário.");
        }

    }

    @Override
    public boolean checkUserExist(String userEmail) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        List<Users> result = helper.queryUserEmail(userEmail);

        if ( result.size() == 0){
            Log.i(TAG,"Não existe: "+ userEmail );
            return false;
        }else{
            Log.i(TAG,"Já existe: "+ userEmail + " = " + result.size());
            return true;
        }
    }

    @Override
    public void addUser(Users user) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getApplicationContext());
        helper.insertUser(user);
    }
}
