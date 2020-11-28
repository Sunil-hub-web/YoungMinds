package com.egk.egk;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;

public class OTP extends AppCompatActivity {

    EditText edotp;
    ViewDialog progressDialog;
    String uid;
    SessionManager session;
    RelativeLayout btn_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        session = new SessionManager(this);

        progressDialog = new ViewDialog(this);

        uid = getIntent().getStringExtra("uid");

        edotp = (EditText) findViewById(R.id.edotp);

      btn_verify = (RelativeLayout) findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edotp.getText().length()==0){
                    edotp.setError("enter otp");
                }else{
                    String ot = edotp.getText().toString();
                    VerifyOTP(ot, uid);
                }
            }
        });

    }

    public void VerifyOTP(String otp, String userid){

        progressDialog.showDialog();

        String a = "{\"user_id\":\"";
        String b = "\", \"otp\":\"";
        String h = "\"}";

        String url = "https://egknow.com/service-web/webservice.php?method=verifyUser&data=" + a + userid + b + otp + h;
        Log.d("RanjeetUrlCheck", url);

        url = url.replaceAll("\\s", "%20");


        Log.d("Receive", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();

                        try {

                            progressDialog.hideDialog();
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            if (statuse.equalsIgnoreCase("true")) {
//                                String userId = jsonObjMain.getString("u_id");
                                session.setUserID(uid);
                                session.setLogin();

                                Toast.makeText(OTP.this, "Login to continue", Toast.LENGTH_SHORT).show();

                                Intent intentHomePage = new Intent(getApplicationContext(), Login.class);
                                startActivity(intentHomePage);
                                finish();
                            }
                            else{
                                String errro_mesg = jsonObjMain.getString("err_msg");
                                Toast.makeText(OTP.this, errro_mesg, Toast.LENGTH_SHORT).show();
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

}
