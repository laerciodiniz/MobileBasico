package com.example.mobilebasico.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilebasico.R;
import com.example.mobilebasico.database.AppDbHelper;

import java.sql.SQLException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    //Classe principal implementa o contrato da View

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.edtName)
    EditText mUserName;
    @BindView(R.id.edtEmail)
    EditText mUserEmail;
    @BindView(R.id.edtPassword)
    EditText mUserPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    AppDbHelper appDbHelper;
    RegisterPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        mPresenter = new RegisterPresenter(this, appDbHelper);

    }

   @OnClick(R.id.btnRegister)
    public void btnRegisterClick() {
       try {
           mPresenter.checkValues(
                   mUserName.getText().toString(),
                   mUserEmail.getText().toString(),
                   mUserPassword.getText().toString() );
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
