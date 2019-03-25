package com.example.mobilebasico.ui.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.os.Bundle;

import com.example.mobilebasico.R;

public class EventActivity extends AppCompatActivity {

    private int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mUserId = getIntent().getExtras().getInt("USER_ID");

        EventFragment eventFragment = new EventFragment();
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        fragment.replace(R.id.frameLayout, eventFragment);
        fragment.commit();

    }
}
