package com.egk.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.egk.adapter.Transaction_Adapter;
import com.egk.egk.R;
import com.egk.egk.Transaction_list_item;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.SessionManager;


public class My_Transaction extends Fragment {
    ArrayList<Transaction_list_item> joblist = new ArrayList<>();

    String[] name = {
            "pa",
            "ja",
            "Profile",
            "Topup",
            "Offers",
            "Notifications"
    };
    RecyclerView recyclerView;
    SessionManager session;
    ProgressDialog pDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_my__transaction, container, false);

        session = new SessionManager(getActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.rcy_transaction);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;

            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);


//                if (position == 0) {
//                    Intent i = new Intent(getActivity(), Transaction.class);
//                    startActivity(i);
//
//
//                } else if (position == 1) {
//                    Intent j = new Intent(getActivity(), Transaction.class);
//                    startActivity(j);
//
//
//                }

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));
        ProductList();
        return v;
    }

    public void ProductList() {

        String a = "{\"user_id\":\"";
        String b = "\"}";

        String url = "https://egknow.com/service-web/webservice.php?method=userTransactionHistory&data="+a+session.getUserID()+b;

        if(url.contains(" ")) {
            url = url.replace(" ", "%20");
        }else{
            url = url;
        }
        Log.d("TransactionHistory",url);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading, Please wait...");
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

//        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TransactionHistoryresp", response.toString());
                        String REsult = response.toString();

//                        pDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(REsult);
                            String status= jsonObject.getString("success");
                            if(status.equalsIgnoreCase("true")) {

                                JSONArray jsonArrayMain = jsonObject.getJSONArray("userTransactionList");

                                if(jsonArrayMain.length()==0){
                                    Toast.makeText(getActivity(), "No transactions", Toast.LENGTH_SHORT).show();
                                }else {

                                    for (int i = 0; i < jsonArrayMain.length(); i++) {

                                        JSONObject jsobjectitem = jsonArrayMain.getJSONObject(i);

                                        String id = jsobjectitem.getString("id");
                                        String transaction_type = jsobjectitem.getString("transaction_type");
                                        String package_id = jsobjectitem.getString("package_id");
                                        String package_duration = jsobjectitem.getString("package_duration");
                                        String transaction_amount = jsobjectitem.getString("transaction_amount");
                                        String transaction_id = jsobjectitem.getString("transaction_id");
                                        String transaction_datetime = jsobjectitem.getString("transaction_datetime");
                                        String transaction_status = jsobjectitem.getString("transaction_status");


                                        Transaction_list_item pd = new Transaction_list_item(id, transaction_type, package_id, package_duration, transaction_amount, transaction_id, transaction_datetime, transaction_status);
                                        joblist.add(pd);
                                    }

                                    Transaction_Adapter adapter = new Transaction_Adapter(joblist, My_Transaction.this);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    recyclerView.setAdapter(adapter);
                                }
                                pDialog.dismiss();

                            }

                        } catch (Exception e) {
                            Log.d("Ranjeet", "ranjeet Error" + e.toString());
                            pDialog.dismiss();

                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("edghyt", "Error: " + error.getMessage());
                // hide the progress dialog

                pDialog.dismiss();
//                Categories();
//                openDialg();
//                getValues();


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
//                    openDialg();
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

}
