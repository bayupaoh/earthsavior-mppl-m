package id.or.codelabs.earthsavior;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.SignInButton;

public class ActivityLogin extends Activity {

    SignInButton login;
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

        login = (SignInButton) findViewById(R.id.sign_in_google);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDashboard = new Intent(getApplicationContext(), DahsboardActivity.class);
                startActivity(intentDashboard);
            }
        });

    }
}
