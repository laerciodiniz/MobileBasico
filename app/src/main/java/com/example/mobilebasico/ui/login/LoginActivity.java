package com.example.mobilebasico.ui.login;

import android.content.Intent;

import com.example.mobilebasico.R;
import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.ui.event.EventActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.edtEmail)
    EditText mEmailView;
    @BindView(R.id.edtPassword)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;

    AppDbHelper appDbHelper;
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mPresenter = new LoginPresenter(this, appDbHelper);

    }

    @OnClick(R.id.btnLogin)
    public void btnLoginClick()  {
        try {
            mPresenter.checkLogin(
                    mEmailView.getText().toString(),
                    mPasswordView.getText().toString() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

