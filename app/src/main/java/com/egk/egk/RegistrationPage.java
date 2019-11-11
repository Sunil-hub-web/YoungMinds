package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.egk.extra.AppSingleton;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;

public class RegistrationPage extends AppCompatActivity {
    TextView txt_sinin;
    RelativeLayout btn_signUp;

    EditText edt_name, edt_mob_no, edt_email, edt_pswd, edt_confrin_pswd;
    ViewDialog progressDialog;
    SessionManager session;
    String emailpattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        session = new SessionManager(getApplicationContext());



        progressDialog = new ViewDialog(this);
        edt_name = findViewById(R.id.edt_name);
        edt_mob_no = findViewById(R.id.edt_mob_no);
        edt_email = findViewById(R.id.edt_email);
        edt_pswd = findViewById(R.id.edt_pswd);
        edt_confrin_pswd = findViewById(R.id.edt_confirm_pswd);
        btn_signUp = findViewById(R.id.btn_signUp);
        txt_sinin = findViewById(R.id.txt_sinin);
        txt_sinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetnLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(intetnLogin);
            }
        });

        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().length() == 0) {
                    Toast.makeText(RegistrationPage.this, "ENter Name", Toast.LENGTH_SHORT).show();
                } else if (edt_mob_no.getText().length() == 0) {
                    Toast.makeText(RegistrationPage.this, "Enter Mobile No", Toast.LENGTH_SHORT).show();

                } else if (edt_mob_no.getText().length()!=10){

                    Toast.makeText(getApplicationContext(), "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();

                } else if (isValidPhoneNumber(edt_mob_no.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                }

                else if (!edt_email.getText().toString().matches(emailpattern)) {
                    Toast.makeText(RegistrationPage.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else  if (edt_pswd.getText().length() == 0) {
                    Toast.makeText(RegistrationPage.this, "Enter Password ", Toast.LENGTH_SHORT).show();
                } else if (edt_confrin_pswd.getText().length() == 0) {
                    Toast.makeText(RegistrationPage.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }

                else if(!edt_pswd.getText().toString().equals(edt_confrin_pswd.getText().toString())){
                    Toast.makeText(RegistrationPage.this, "Password and Confirm PAssword Should Match", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Intent intentHomePage = new Intent(getApplicationContext(),Egk_nav.class);
//                    startActivity(intentHomePage);

                    getregistrer(edt_name.getText().toString(), edt_mob_no.getText().toString(), edt_pswd.getText().toString(), edt_email.getText().toString());
                }


            }
        });


    }




    public void getregistrer(String name, String mobile, String pasword, String email) {
        progressDialog.showDialog();

        String a = "{\"name\":\"";
        String b = "\",\"mobile\":\"";
        String c = "\",\"email\":\"";
        String d = "\",\"password\":\"";
        String e = "\",\"state_id\":\"";
        String f = "\",\"city\":\"";
        String g = "\",\"address\":\"";
        String i = "\",\"occupation\":\"";
        String j = "\",\"dob\":\"";
        String h = "\"}";

        String url = "https://egknow.com/service-web/webservice.php?method=userRegistration&data=" + a + name + b + mobile + c + email + d + pasword
                + e + " " + f + " " + g + " " + i + " " + j + " " + h;
        Log.d("RanjeetUrlCheck", url);

        url = url.replaceAll("\\s", "%20");


        Log.d("Receive", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("registerresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();

                        try {

                            progressDialog.hideDialog();
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            if (statuse.equalsIgnoreCase("true")) {
                                String userId = jsonObjMain.getString("u_id");
                                String otp = jsonObjMain.getString("opt");
//                                session.setUserID(userId);

                                Intent intentHomePage = new Intent(getApplicationContext(), OTP.class);
                                intentHomePage.putExtra("uid",userId);
                                startActivity(intentHomePage);
//                                finish();
                            }
                            else{
                                String errro_mesg = jsonObjMain.getString("err_msg");
                                Toast.makeText(RegistrationPage.this, errro_mesg, Toast.LENGTH_SHORT).show();
                            }
//
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getApplicationContext(), "Not Registered Succesfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog

//               getValues();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
}
