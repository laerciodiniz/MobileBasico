package com.example.mobilebasico.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mobilebasico.R;
import com.example.mobilebasico.config.SettingsActivity;
import com.example.mobilebasico.ui.event.EventActivity;
import com.example.mobilebasico.ui.login.LoginActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int HOME_ID = 1;
    private static final int EVENT_ID = 2;
    private static final int SETTINGS_ID = 3;

    private int mUserId;
    Drawer mDrawer = null;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mUserId = getIntent().getExtras().getInt("USER_ID");
        Log.i(TAG,"USUARIO LOGADO:" + mUserId);

        setSupportActionBar(toolbar);

        mDrawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIdentifier(1).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName("Event").withIdentifier(2).withIcon(FontAwesome.Icon.faw_sticky_note),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings").withIdentifier(3).withIcon(FontAwesome.Icon.faw_cog))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if ( drawerItem != null ){

                            Intent intent = null;

                            if ( drawerItem.getIdentifier() == HOME_ID ) {
                                intent = new Intent(MainActivity.this, MainActivity.class);
                            }else if ( drawerItem.getIdentifier() == EVENT_ID ){
                                intent = new Intent(MainActivity.this, EventActivity.class);
                            }else if ( drawerItem.getIdentifier() == SETTINGS_ID ){
                                intent = new Intent(MainActivity.this, SettingsActivity.class);
                            }

                            if ( intent != null ){
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = mDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if ( mDrawer != null && mDrawer.isDrawerOpen() ){
            mDrawer.closeDrawer();
        }else{
            super.onBackPressed();
        }
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
                System.exit(0); //Finaliza o App
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
