package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;

import org.json.JSONObject;

public class ConfirmPassword extends AppCompatActivity {
    EditText edt_newpass,edt_cnf_pass;
    RelativeLayout btn_cnf_submit;
    ViewDialog progressDialog;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog=new ViewDialog(this);
        uid = getIntent().getStringExtra("USERID");


        //Ranjeet sending the value




        edt_newpass=(EditText)findViewById(R.id.edt_newpass);
        edt_cnf_pass=(EditText)findViewById(R.id.edt_cnf_pass);

        btn_cnf_submit=(RelativeLayout) findViewById(R.id.btn_cnf_submit);

        btn_cnf_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_newpass.getText().length() == 0) {
                    Toast.makeText(ConfirmPassword.this, "Enter New Password", Toast.LENGTH_SHORT).show();

                } else if (edt_cnf_pass.getText().length() == 0) {
                    Toast.makeText(ConfirmPassword.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                }else if  (!validate()){

                    Toast.makeText(getApplicationContext(),"Confirm and new Password didn't match",Toast.LENGTH_SHORT).show();
                }else {

                    getConfirmedPassword(edt_newpass.getText().toString(),edt_cnf_pass.getText().toString());


                }
            }
        });

    }

    private boolean validate() {
        boolean temp=true;

        String newpass=edt_newpass.getText().toString();
        String cpass=edt_cnf_pass.getText().toString();

        if(!newpass.equals(cpass)){
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }

    public void getConfirmedPassword(String newPassword,String confirmPassword){

        String a = "{\"new_password\":\"";
        String b = "\",\"u_id\":\"";
        String d = "\",\"confirm_password\":\"";
        String c = "\"}";
        String url ="https://egknow.com/service-web/webservice.php?method=changeUserForgotPassword&data="+a+newPassword+b+uid+d+confirmPassword+c;
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

                                String uid = jsonObjMain.getString("success_msg");
                                Toast.makeText(ConfirmPassword.this, uid, Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(ConfirmPassword.this,Login.class);

                                startActivity(intent);
                                finish();
                            }



                            else if (statuse.equalsIgnoreCase("false")) {
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
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);


    }

}