package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.egk.extra.ViewDialog;

public class Forgot_Pswd extends AppCompatActivity {
EditText edt_efepswd;
RelativeLayout btn_efesubmit;

ViewDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_forgot__pswd);
        edt_efepswd=(EditText)findViewById(R.id.edt_efepswd);
        btn_efesubmit=(RelativeLayout)findViewById(R.id.btn_efesubmit);

        progressDialog=new ViewDialog(this);
        btn_efesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_efepswd.getText().length() == 0) {
                    Toast.makeText(Forgot_Pswd.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            }
            else {
                    getpassword(edt_efepswd.getText().toString());

                }
                }

        });

    }



    public void getpassword(String email) {
        String a = "{\"mobile\":\"";
        String b = "\"}";
        String url ="https://egknow.com/service-web/webservice.php?method=userForgotPassword&data="+a+email+b;
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
                        progressDialog.hideDialog();
                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if(statuse.equals("true")){

                                String uid = jsonObjMain.getString("u_id");
                                Intent intent=new Intent(Forgot_Pswd.this,ForgotOTP.class);
                                intent.putExtra("USERID",uid);
                                intent.putExtra("mobile",edt_efepswd.getText().toString());
                                startActivity(intent);
                                finish();
                            }



                            if (statuse.equalsIgnoreCase("false")) {
                                String err_msg = jsonObjMain.getString("err_msg");

                               Toast.makeText(getApplicationContext(), "This mobile number is not registered", Toast.LENGTH_SHORT).show();

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
