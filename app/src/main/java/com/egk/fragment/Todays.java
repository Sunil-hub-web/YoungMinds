package com.egk.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.egk.adapter.TodaysAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.TodaysGetSet;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class Todays extends Fragment {

RecyclerView recyclerView;
    ArrayList<TodaysGetSet> todaysGet = new ArrayList<TodaysGetSet>();
    ViewDialog progressDialog;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_todays, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.todays_recycle);
        progressDialog=new ViewDialog(getActivity());

        getTodaysList();

        return v;
    }

    public void  getTodaysList(){

        String url = "https://egknow.com/service-web/webservice.php?method=getsToday" ;
        Log.d("Todays", url);

        progressDialog.showDialog();

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Todaysresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("todaysList");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String todays_id = jsonSubJson.getString("todays_id");
                                    String todays_date = jsonSubJson.getString("todays_date");
                                    String description = jsonSubJson.getString("description");

                                    todaysGet.add(new TodaysGetSet(todays_id, todays_date, description));
                                }
                                TodaysAdapter adapter = new TodaysAdapter(todaysGet,getActivity());
                                recyclerView.setHasFixedSize(true);
                                layoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(adapter);
                            }else if (statuse.equalsIgnoreCase("false")){
                                String err_msg=jsonObjMain.getString("err_msg");
                                Toast.makeText(getActivity(), err_msg, Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("catcherro", "ranjeet Error" + r.toString());
                            Toast.makeText(getActivity(), "Error in Catch", Toast.LENGTH_SHORT).show();
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
