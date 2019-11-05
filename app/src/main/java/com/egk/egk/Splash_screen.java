package com.egk.egk;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;

import com.egk.activites.GetStartedPage;
import com.egk.extra.SessionManager;

public class Splash_screen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        session = new SessionManager(getApplicationContext());

        if(session.isLogin()){
            Intent intent = new Intent(getApplicationContext(),Egk_nav.class);
            startActivity(intent);
            finish();
        }
        else {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(Splash_screen.this, GetStartedPage.class);
                    Splash_screen.this.startActivity(mainIntent);
                    Splash_screen.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }
}
