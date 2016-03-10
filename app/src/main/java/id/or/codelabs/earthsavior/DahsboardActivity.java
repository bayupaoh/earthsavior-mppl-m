package id.or.codelabs.earthsavior;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import id.or.codelabs.earthsavior.Fragment.HomeFragment;
import id.or.codelabs.earthsavior.Fragment.ProfilFragment;

public class DahsboardActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dahsboard);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        navigationView = (NavigationView) findViewById(R.id.fragment_navigation_drawer);
        navigationView.setNavigationItemSelectedListener(navItemSelect);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();

    }

    NavigationView.OnNavigationItemSelectedListener navItemSelect = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setCheckable(true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            drawer.closeDrawer(GravityCompat.START);

            switch (menuItem.getItemId()){
                case R.id.id_menu_home:
                    fragmentManager.beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();
                    return true;
                case R.id.id_menu_chart :

                    return true;
                case R.id.id_menu_profil :
                    fragmentManager.beginTransaction().replace(R.id.container_body, new ProfilFragment()).commit();
                    return true;
                case R.id.id_menu_setting:
                    return true;
                case R.id.id_menu_logout :
                    finish();
                    Intent intentLogin = new Intent(DahsboardActivity.this, ActivityLogin.class);
                    startActivity(intentLogin);
                    return true;
                default:
                    return true;
            }
        }
    };

}
