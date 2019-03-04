package com.example.mobilebasico.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilebasico.R;
import com.example.mobilebasico.ui.login.LoginActivity;
import com.example.mobilebasico.ui.register.RegisterActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {

    private static final String TAG = IntroActivity.class.getSimpleName();

    @BindView(R.id.text_Enter)
    TextView mTextEnter;
    @BindView(R.id.btn_Register)
    Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_cadastro);

        ButterKnife.bind(this);

        mTextEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

}
