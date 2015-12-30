package com.dmariani.androidui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dmariani.androidui.R;
import com.dmariani.androidui.fragment.CameraFragment;
import com.dmariani.androidui.fragment.SimpleTextFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityNavigationInterface {

    /**
     * Views
     */
    private DrawerLayout drawer;
    private FloatingActionButton floatingButton;

    /**
     * Attributes
     */
    private int currentNavigationOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingButton = (FloatingActionButton) findViewById(R.id.floating_button);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Some information to display", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragment)
                .commit();
    }

    /**
     * MENU
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * NAVIGATION MENU
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (currentNavigationOption == item.getItemId()) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        currentNavigationOption = item.getItemId();
        floatingButton.setVisibility(View.VISIBLE);

        if (currentNavigationOption == R.id.nav_camera) {
            floatingButton.setVisibility(View.GONE);
            navigateToCamera();
        } else if (currentNavigationOption == R.id.nav_gallery) {
            navigateToGallery();
        } else if (currentNavigationOption == R.id.nav_music) {
            navigateToMusic();
        } else if (currentNavigationOption == R.id.nav_radio) {
            navigateToRadio();
        } else if (currentNavigationOption == R.id.nav_option1) {
            navigateToAndroid(getString(R.string.nav_menu_option1));
        } else if (currentNavigationOption == R.id.nav_option2) {
            navigateToAndroid(getString(R.string.nav_menu_option2));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void navigateToCamera() {
        setContentFragment(new CameraFragment());
    }

    @Override
    public void navigateToGallery() {
        setContentFragment(SimpleTextFragment.newInstance(getString(R.string.nav_menu_gallery)));
    }

    @Override
    public void navigateToMusic() {
        setContentFragment(SimpleTextFragment.newInstance(getString(R.string.nav_menu_music)));
    }

    @Override
    public void navigateToRadio() {
        setContentFragment(SimpleTextFragment.newInstance(getString(R.string.nav_menu_radio)));
    }

    @Override
    public void navigateToAndroid(String message) {
        setContentFragment(SimpleTextFragment.newInstance(message));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
