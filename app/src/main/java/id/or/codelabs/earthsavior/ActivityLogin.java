package id.or.codelabs.earthsavior;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import id.or.codelabs.earthsavior.Utils.SessionManager;

public class ActivityLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton login;
    GoogleApiClient mGoogleApiClient;
    final int RC_SIGN_IN =0;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(!preferences.getBoolean("onboarding_complete",false)){

            Intent onboarding = new Intent(this, IntroActivity.class);
            startActivity(onboarding);

            finish();
            return;
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(ActivityLogin.this)
                .enableAutoManage(ActivityLogin.this,ActivityLogin.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        login = (SignInButton) findViewById(R.id.sign_in_google);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult hasil = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(hasil);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String nama = acct.getDisplayName();
            String email = acct.getEmail();
            String id = acct.getId();
            String foto = String.valueOf(acct.getPhotoUrl());
            int point=100;

            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.createLoginSession(id,nama,email,foto,100);
            Intent intentDashboard = new Intent(ActivityLogin.this, DahsboardActivity.class);
            startActivity(intentDashboard);
        } else {
            Toast.makeText(getApplicationContext(),result.getStatus().getStatusMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
