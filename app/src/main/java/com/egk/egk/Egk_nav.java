package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.egk.adapter.Notification_Adapter;
import com.egk.extra.AppSingleton;
import com.egk.extra.Constants;
import com.egk.extra.SessionManager;
import com.egk.fragment.BasicGk;
import com.egk.fragment.ChangePassword;
import com.egk.fragment.EgkQuiz;
import com.egk.fragment.GkItemCategory;
import com.egk.fragment.Glossary;
import com.egk.fragment.HomeFragment;
import com.egk.fragment.MatchPoint;
import com.egk.fragment.MonthlyGk;
import com.egk.fragment.MyProfile;
import com.egk.fragment.My_Notifications;
import com.egk.fragment.My_Offers;
import com.egk.fragment.My_Topup;
import com.egk.fragment.My_Transaction;
import com.egk.fragment.PreviousTestPAper_Fragment;
import com.egk.fragment.PrivacyPolicyFragment;
import com.egk.fragment.Recharge_point;
import com.egk.fragment.Report;
import com.egk.fragment.Todays;
import com.egk.fragment.Upcoming_Exam;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.egk.egk.R.id.screen_area;

public class Egk_nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Boolean exit = false;
    SessionManager sesion;

    public static TextView headtitle, noticount;
    public static ImageView notification, egk_logo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egk_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sesion = new SessionManager(this);
        notification = (ImageView) findViewById(R.id.notification);
        egk_logo = (ImageView) findViewById(R.id.egk_logo);
        headtitle = (TextView) findViewById(R.id.title);
        noticount = (TextView) findViewById(R.id.noticount);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                My_Notifications fragment = new My_Notifications();
                fm.beginTransaction().replace(R.id.screen_area, fragment).commit();
                headtitle.setText("Notifications");
                egk_logo.setVisibility(View.GONE);
            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageView menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        navigationView.setItemIconTintList(null);


//        headtitle.setText("Home");
        TextView name = (TextView) hView.findViewById(R.id.nametext);
        name.setText(sesion.getUserName());

        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);


    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = null;
        try {

            currentFragment = getSupportFragmentManager().findFragmentById(R.id.screen_area);
            Log.d("BackPressedFragment", currentFragment.toString());
        } catch (Exception e) {
            Log.d("BackPressedFragment", e.toString());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (currentFragment instanceof Todays) {

            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

            //       getFragmentValue( new MyProfileFragment());

        } else if (currentFragment instanceof GkItemCategory) {

            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

            //       getFragmentValue( new MyProfileFragment());

        } else if (currentFragment instanceof My_Transaction) {

            Log.d("BackPressedFragment", "ranjeet enetered here done");
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();


        } else if (currentFragment instanceof MyProfile) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();


        } else if (currentFragment instanceof My_Topup) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();


        } else if (currentFragment instanceof My_Offers) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();


        } else if (currentFragment instanceof My_Notifications) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();


        } else if (currentFragment instanceof PreviousTestPAper_Fragment) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();
        } else if (currentFragment instanceof ChangePassword) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();
        } else if (currentFragment instanceof EgkQuiz) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof PrivacyPolicyFragment) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof MonthlyGk) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof Report) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof Glossary) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof MatchPoint) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof Recharge_point) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof BasicGk) {

            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (currentFragment instanceof Upcoming_Exam) {
            Intent intent = new Intent(getApplicationContext(), Egk_nav.class);
            startActivity(intent);
            finish();

        } else if (exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_egk_nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        //  automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //  noinspection SimplifiableIfStatement
        //  if (id == R.id.action_settings) {
        //    return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            headtitle.setText("");
            egk_logo.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_today) {
            if (sesion.getToday().equalsIgnoreCase("1") || (sesion.getToday().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Today's");
                egk_logo.setVisibility(View.GONE);
                fragment = new Todays();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_gk) {
            if (sesion.getCategogryGK().equalsIgnoreCase("1") || (sesion.getCategogryGK().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Category GK");
                egk_logo.setVisibility(View.GONE);
                fragment = new GkItemCategory();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_test_paper) {
            if (sesion.getSamplePaper().equalsIgnoreCase("1") || (sesion.getSamplePaper().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Sample Papers");
                egk_logo.setVisibility(View.GONE);
                fragment = new PreviousTestPAper_Fragment();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_upcoming) {
            if (sesion.getUpcomingExam().equalsIgnoreCase("1") || (sesion.getUpcomingExam().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Upcoming Exam");
                egk_logo.setVisibility(View.GONE);
                fragment = new Upcoming_Exam();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_basicgk) {
            if (sesion.getBasicGK().equalsIgnoreCase("1") || (sesion.getBasicGK().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Basic GK");
                egk_logo.setVisibility(View.GONE);
                fragment = new BasicGk();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_rechargepoint) {
            headtitle.setText("Recharge Point");
            egk_logo.setVisibility(View.GONE);
            fragment = new Recharge_point();

        } else if (id == R.id.nav_eqkquiz) {
            if (sesion.getQuiz().equalsIgnoreCase("1") || (sesion.getQuiz().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Egk Quiz");
                egk_logo.setVisibility(View.GONE);
                fragment = new EgkQuiz();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_offers) {
            headtitle.setText("My Offers");
            egk_logo.setVisibility(View.GONE);
            fragment = new My_Offers();

        } else if (id == R.id.nav_notification) {
            headtitle.setText("Notifications");
            egk_logo.setVisibility(View.GONE);
            fragment = new My_Notifications();
        } else if (id == R.id.nav_match_point) {
            if (sesion.getMatchPoint().equalsIgnoreCase("1") || (sesion.getMatchPoint().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Match Point");
                egk_logo.setVisibility(View.GONE);
                fragment = new MatchPoint();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_transactions) {
            headtitle.setText("Transactions");
            egk_logo.setVisibility(View.GONE);
            fragment = new My_Transaction();
        } else if (id == R.id.nav_topup) {
            headtitle.setText("Top Up");
            egk_logo.setVisibility(View.GONE);
            fragment = new My_Topup();
        } else if (id == R.id.nav_monthly_gk) {
            if (sesion.getMonthlyGk().equalsIgnoreCase("1") || (sesion.getMonthlyGk().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Monthly GK");
                egk_logo.setVisibility(View.GONE);
                fragment = new MonthlyGk();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_report) {
            if (sesion.getReports().equalsIgnoreCase("1") || (sesion.getReports().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Report");
                egk_logo.setVisibility(View.GONE);
                fragment = new Report();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_glossary) {
            if (sesion.getGlossary().equalsIgnoreCase("1") || (sesion.getGlossary().equalsIgnoreCase("0") && sesion.getSubcription().equalsIgnoreCase("active"))) {
                headtitle.setText("Glossary");
                egk_logo.setVisibility(View.GONE);
                fragment = new Glossary();
            } else {

                Toast.makeText(getApplicationContext(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_profile) {
            headtitle.setText("Profile");
            egk_logo.setVisibility(View.GONE);
            fragment = new MyProfile();

        } else if (id == R.id.nav_changepass) {
            headtitle.setText("Change Password");
            egk_logo.setVisibility(View.GONE);
            fragment = new ChangePassword();

        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here");
            String app_url = "https://play.google.com/store/apps/details?id=com.egk.egk&hl=en";
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

        } else if (id == R.id.nav_send_privacy) {
            headtitle.setText("Privacy Policy");
            egk_logo.setVisibility(View.GONE);
            fragment = new PrivacyPolicyFragment();

        } else if (id == R.id.nav_logout) {
            sesion.logoutUser();
            finish();

        }

        if (fragment != null) {

            getFragmentValue(fragment);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void getFragmentValue(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(screen_area, fragment);
        ft.commit();

    }

    private void displaySelectedScreen(int itemId) {

        Fragment fragmentt = null;

        //initializing the fragment object which is selected
        switch (itemId) {


            case R.id.nav_home:

                fragmentt = new HomeFragment();
                egk_logo.setVisibility(View.VISIBLE);

                break;


        }

        //replacing the fragment
        if (fragmentt != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(screen_area, fragmentt);
            ft.commit();

        }
    }

    public void getNotification() {

        String url = "https://egknow.com/service-web/webservice.php?method=getusrNotifCount&data={\"user_id\":\"" + sesion.getUserID() + "\"}";

        Log.d("notification", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(Egk_nav.this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("notificationresponse", response.toString());
                        String REsult = response.toString();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {
                                String notification_cnt = jsonObjMain.getString("notification_cnt");
                                if (notification_cnt.equalsIgnoreCase("0")) {
                                    noticount.setText("");
                                } else {
                                    noticount.setText(notification_cnt);
                                }
                            }


                        } catch (Exception r) {

                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
//                            Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog

//               getValues();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {


                    // ...
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(Egk_nav.this).addToRequestQueue(jsonObjReq, url);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotification();
    }


}