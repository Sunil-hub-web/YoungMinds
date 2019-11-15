package com.egk.egk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Login extends AppCompatActivity {
    EditText edtl_email, edtl_pswd;
    RelativeLayout btn_login;
    TextView txt_signup,txt_fgtpswd;

    SessionManager session;
    ViewDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        session = new SessionManager(getApplicationContext());

        progressDialog = new ViewDialog(this);

        edtl_email = (EditText) findViewById(R.id.edtl_email);
        edtl_pswd = (EditText) findViewById(R.id.edtl_pswd);
        btn_login = (RelativeLayout) findViewById(R.id.btn_login);
        txt_fgtpswd=(TextView)findViewById(R.id.txt_fgtpswd);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegsiter = new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(intentRegsiter);
            }
        });
        txt_fgtpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentfgtpswd = new Intent(getApplicationContext(), Forgot_Pswd.class);
                startActivity(intentfgtpswd);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtl_email.getText().length()==0) {
                    Toast.makeText(Login.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else if (edtl_email.getText().length()!=10) {
                    Toast.makeText(Login.this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (edtl_pswd.getText().length() == 0) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {

                    //    Intent i = new Intent(Login.this, Egk_nav.class);
                    //      startActivity(i);

                    getlogin(edtl_email.getText().toString(),edtl_pswd.getText().toString());
                }
            }
        });

    }

    public void getlogin(String email, String password) {
        String a = "{\"email\":\"";
        String b = "\",\"password\":\"";
        String c = "\"}";
        String url = "https://egknow.com/service-web/webservice.php?method=getLoginDetails&data=" + a + email + b + password + c;
        Log.d("RanjeetUrlCheck", url);

        progressDialog.showDialog();

        Log.d("Receive", url);

        if(url.contains(" ")){
            url = url.replace(" ", "%20");
        }else{
            url=url;
        }

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");


                            if (statuse.equalsIgnoreCase("true")) {
                                String userId = jsonObjMain.getString("u_id");
                                String user_name = jsonObjMain.getString("user_name");
                                String email = jsonObjMain.getString("email");
                                String mobile = jsonObjMain.getString("mobile");
                                String state = jsonObjMain.getString("state");
                                String city = jsonObjMain.getString("city");
                                String address = jsonObjMain.getString("address");
                                String occupation = jsonObjMain.getString("occupation");
                                String dob = jsonObjMain.getString("dob");


                                session.setUserID(userId);
                                session.setUserName(user_name);
                                session.setUserEmail(email);
                                session.setUserPhonenumber(mobile);
                                session.setLogin();

                                Intent intetLogin  = new Intent(getApplicationContext(),Egk_nav.class);
                                startActivity(intetLogin);
                                finish();

                            }

                            else{
                                String erro_msg = jsonObjMain.getString("err_msg");
                                Toast.makeText(Login.this, erro_msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hideDialog();
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
                500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);
    }


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
