package com.example.mobilebasico.ui.login;

import android.content.Intent;

import com.example.mobilebasico.R;
import com.example.mobilebasico.ui.event.EventActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    @BindView(R.id.edtEmail)
    EditText mEmailView;
    @BindView(R.id.edtPassword)
    EditText mPasswordView;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventActivity.class));
            }
        });
    }

}

