package id.or.codelabs.earthsavior;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;

import id.or.codelabs.earthsavior.Fragment.ChartFragment;
import id.or.codelabs.earthsavior.Fragment.HomeFragment;
import id.or.codelabs.earthsavior.Fragment.ProfilFragment;

public class DahsboardActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar mToolbar;
    GoogleApiClient mGoogleApiClient;

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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
                    fragmentManager.beginTransaction().replace(R.id.container_body, new ChartFragment()).commit();
                    return true;
                case R.id.id_menu_profil :
                    fragmentManager.beginTransaction().replace(R.id.container_body, new ProfilFragment()).commit();
                    return true;
                case R.id.id_menu_logout :
                    revokeAccess();
                    return true;
                default:
                    return true;
            }
        }
    };

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(new ResultCallbacks<Status>() {
            @Override
            public void onSuccess(@NonNull Status status) {
                finish();
                Intent intentLogin = new Intent(DahsboardActivity.this, ActivityLogin.class);
                startActivity(intentLogin);
            }

            @Override
            public void onFailure(@NonNull Status status) {
                Snackbar.make(getCurrentFocus(),"LOG OUT GAGAL",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
