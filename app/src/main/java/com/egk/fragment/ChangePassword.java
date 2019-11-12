package com.egk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;


public class ChangePassword extends Fragment {

    EditText edt_old_pass,edt_new_pswd,edt_cnf_pswd;
    RelativeLayout btn_update;
    SessionManager sessionManager;

    ViewDialog viewDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the viewquiz for this fragment
        View v=inflater.inflate(R.layout.fragment_change_password, container, false);

        edt_old_pass=(EditText)v.findViewById(R.id.edt_old_pass);
        edt_new_pswd=(EditText)v.findViewById(R.id.edt_new_pswd);
        edt_cnf_pswd=(EditText)v.findViewById(R.id.edt_cnf_pswd);
        btn_update=(RelativeLayout)v.findViewById(R.id.btn_update);
        viewDialog = new ViewDialog(getActivity());

        sessionManager = new SessionManager(getActivity());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_old_pass.getText().length()==0){
                    Toast.makeText(getActivity(), "Enter Valid Old Password", Toast.LENGTH_SHORT).show();

                }else if(edt_new_pswd.getText().length()==0){
                    Toast.makeText(getActivity(), "Enter New Password", Toast.LENGTH_SHORT).show();

                }else if(edt_cnf_pswd.getText().length()==0){
                    Toast.makeText(getActivity(), "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                } else if (!validate()) {
                    Toast.makeText(getActivity(),"Confirm and new Password didn't match",Toast.LENGTH_SHORT).show();

                }else {

                    Changepass();

                }

            }
        });

        return v;
    }

    public  void Changepass(){

        String a = "{\"u_id\":\"";
        String b = "\",\"old_password\":\"";
        String c = "\",\"new_password\":\"";
        String d = "\",\"confirm_password\":\"";
        String e = "\"}";

        String url="https://egknow.com/service-web/webservice.php?method=changeUserPassword&data="+a+sessionManager.getUserID()+
                b+edt_old_pass.getText().toString()+c+edt_new_pswd.getText().toString()+d+edt_cnf_pswd.getText().toString()+e;

        viewDialog.showDialog();
        Log.d("ChangePasswordUrl",url);


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ChangePassword", response.toString());
                        String REsult = response.toString();

                        try {

                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            if (statuse.equalsIgnoreCase("true")) {


                                Toast.makeText(getActivity(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                sessionManager.logoutUser();

                            }else if (statuse.equalsIgnoreCase("false")){
                                String message=jsonObjMain.getString("msg");
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception r){
                            viewDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());

                            Toast.makeText(getActivity(), "Password not Changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ranjeet", "Error: " + error.getMessage());
                // hide the progress dialog

//               getValues();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    // ...
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppSingleton.getInstance(getActivity()).addToRequestQueue(jsonObjReq, url);
    }


    private boolean validate() {
        boolean temp=true;

        String newpass=edt_new_pswd.getText().toString();
        String cpass=edt_cnf_pswd.getText().toString();

        if(!newpass.equals(cpass)){
            Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }


}

