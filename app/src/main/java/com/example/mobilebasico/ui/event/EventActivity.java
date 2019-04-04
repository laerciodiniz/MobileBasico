package com.example.mobilebasico.ui.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.os.Bundle;

import com.example.mobilebasico.R;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EventFragment eventFragment = new EventFragment();
        //Recuperando EXTRAS da MainActivity
        eventFragment.setArguments(getIntent().getExtras());

        //Exibe o Fragmento
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, eventFragment)
                .commit();

//        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
//        fragment.replace(R.id.frameLayout, eventFragment);
//        fragment.commit();

    }
}
