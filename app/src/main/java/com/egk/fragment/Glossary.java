package com.egk.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.egk.activites.GlosseryActivity;
import com.egk.activites.MatchPointActivity;
import com.egk.adapter.GlosseryAdapter;
import com.egk.adapter.MonthlyAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.GlosseryGetSet;
import com.egk.gettersetter.MontlyGetSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Glossary extends Fragment {
    RecyclerView recyclerView;
    ViewDialog progressDialog;
    ArrayList<GlosseryGetSet> glosseryGet = new ArrayList<GlosseryGetSet>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_glossary, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.glossery_recycle);
        progressDialog=new ViewDialog(getActivity());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                GlosseryGetSet itemname = glosseryGet.get(position);


                Intent i = new Intent(getActivity(), GlosseryActivity.class);
                i.putExtra("id",itemname.getGlossary_category_id());
                i.putExtra("name",itemname.getName());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));




        getGlosseryData();
        return v;
    }

    public void getGlosseryData() {

        String url = "https://egknow.com/Web_Service/web_service.php?method=getGllosoryCate";

        progressDialog.showDialog();

        Log.d("glossery", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("glosseryrepo", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            glosseryGet.clear();
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("cateList");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String glossary_category_id = jsonSubJson.getString("glossary_category_id");
                                    String name = jsonSubJson.getString("name");


                                    glosseryGet.add(new GlosseryGetSet(glossary_category_id,name));
                                }

                                GlosseryAdapter adapter = new GlosseryAdapter(glosseryGet, getActivity());
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(adapter);
                            }else if (statuse.equalsIgnoreCase("false")){
                                String message=jsonObjMain.getString("msg");
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }

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
