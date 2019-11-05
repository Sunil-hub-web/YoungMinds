package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;

public class ForgotOTP extends AppCompatActivity {

    EditText edt_otp;
    RelativeLayout btn_verify_otp;
    String uid ;
    ViewDialog progressDialog;
    TextView resend_otp;
    String mobilenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_otp);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog=new ViewDialog(this);

        uid = getIntent().getStringExtra("USERID");
        mobilenumber = getIntent().getStringExtra("mobile");

        resend_otp=(TextView)findViewById(R.id.resend_otp);

        edt_otp=(EditText)findViewById(R.id.edt_otp);
        btn_verify_otp=(RelativeLayout)findViewById(R.id.btn_verify_otp);

        btn_verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_otp.getText().length() == 0) {
                    Toast.makeText(ForgotOTP.this, "Enter Otp", Toast.LENGTH_SHORT).show();

                } else {

                    getOtp(edt_otp.getText().toString());


                }

            }
        });

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getpassword();
            }
        });

    }



    public void getOtp(String OTP){

        String a = "{\"otp\":\"";
        String b = "\",\"u_id\":\"";
        String c = "\"}";
        String url ="https://egknow.com/service-web/webservice.php?method=userVerifyForgotPassword&data="+a+OTP+b+uid+c;
        progressDialog.showDialog();
        Log.d("RanjeetUrlCheck", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if(statuse.equals("true")){

                                String uid = jsonObjMain.getString("user_id");
                                Intent intent=new Intent(ForgotOTP.this,ConfirmPassword.class);
                                intent.putExtra("USERID",uid);
                                startActivity(intent);
                                finish();
                            }



                            if (statuse.equalsIgnoreCase("false")) {
                                String userId = jsonObjMain.getString("err_msg");

                                Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getApplicationContext(), "Successfully submited", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog
                progressDialog.hideDialog();
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

    public void getpassword() {
        String a = "{\"mobile\":\"";
        String b = "\"}";
        String url ="https://egknow.com/service-web/webservice.php?method=userForgotPassword&data="+a+mobilenumber+b;
        progressDialog.showDialog();
        Log.d("resendotp", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("resendotpresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();
                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if(statuse.equals("true")){

                                String uid = jsonObjMain.getString("u_id");
                            }



                          if (statuse.equalsIgnoreCase("false")) {
                                String err_msg = jsonObjMain.getString("err_msg");

                                Toast.makeText(getApplicationContext(), err_msg, Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getApplicationContext(), "Successfully submited", Toast.LENGTH_SHORT).show();
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

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);
    }

}

