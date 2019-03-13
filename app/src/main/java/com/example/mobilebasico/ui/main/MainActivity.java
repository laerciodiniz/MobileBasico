package com.example.mobilebasico.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mobilebasico.R;
import com.example.mobilebasico.ui.login.LoginActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private int mUserId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserId = getIntent().getExtras().getInt("USER_ID");
        Log.i(TAG,"USUARIO LOGADO:" + mUserId);

        new DrawerBuilder()
                .withActivity(this)
                .build();

        PrimaryDrawerItem HOME = new PrimaryDrawerItem().withIdentifier(1).withName("HOME");
        SecondaryDrawerItem EVENTS = new SecondaryDrawerItem().withIdentifier(2).withName("EVENT");
        SecondaryDrawerItem SETTINGS = new SecondaryDrawerItem().withIdentifier(3).withName("SETTINGS");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        HOME,
                        new DividerDrawerItem(),
                        EVENTS,
                        new DividerDrawerItem(),
                        SETTINGS)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logoff :
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.menu_exit :
                finish();
                System.exit(0); //Finaliza o App
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
