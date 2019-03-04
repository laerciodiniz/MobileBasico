package com.example.mobilebasico.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilebasico.R;

import java.sql.SQLException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    //Classe principal implementa o contrato da View

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private RegisterContract.Presenter mPresenter;

    @BindView(R.id.edtName)
    EditText mUserName;
    @BindView(R.id.edtEmail)
    EditText mUserEmail;
    @BindView(R.id.edtPassword)
    EditText mUserPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        mPresenter = new RegisterPresenter(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = mUserName.getText().toString();
                String e = mUserEmail.getText().toString();
                String p = mUserPassword.getText().toString();

                try {
                    mPresenter.checkValues( u, e, p );
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

   /* @OnClick(R.id.btnRegister)
    public void btnRegisterClick(){
        mPresenter.checkValues(
                mUserName.getText().toString(),
                mUserEmail.getText().toString(),
                mUserPassword.getText().toString() );
    }*/

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
