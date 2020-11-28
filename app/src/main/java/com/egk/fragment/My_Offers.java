package com.egk.fragment;

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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import com.egk.adapter.Offer_Adapter;
import com.egk.egk.Offer_recy_list;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;


public class My_Offers extends Fragment {
    ArrayList<Offer_recy_list> offedrArraylist = new ArrayList<>();


    ViewDialog progressDialog;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_my__offers, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcy_offers);
        progressDialog = new ViewDialog(getActivity());



        getOfferDetails();
        return v;
    }

    public void getOfferDetails(){

        String url = "https://egknow.com/service-web/webservice.php?method=getUserOffersList";

        progressDialog.showDialog();

        Log.d("offers", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("offersresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");


                            JSONArray jsonArray = jsonObjMain.getJSONArray("userOffersList");
                            for (int i = 0 ;i<jsonArray.length();i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String offerid = jsonSubJson.getString("offer_id");
                                String offerDDesc = jsonSubJson.getString("offer_desc");

                                offedrArraylist.add(new Offer_recy_list(offerid, offerDDesc));
                            }
//                            Collections.reverse(offedrArraylist);
                            Offer_Adapter adapter = new Offer_Adapter(offedrArraylist, My_Offers.this);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adapter);



                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
//                            Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
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

}


