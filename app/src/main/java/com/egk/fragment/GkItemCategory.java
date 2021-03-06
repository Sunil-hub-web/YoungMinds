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
import com.egk.activites.GkCategory;
import com.egk.adapter.Gk_adapter;
import com.egk.egk.Gk_list;
import com.egk.egk.R;
import com.egk.egk.Recy_recy_items;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class GkItemCategory extends Fragment {

    ArrayList<Recy_recy_items> caterogotyArraylist = new ArrayList<>();

    RecyclerView recyclerView;
    ViewDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_gk_category, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.rcy_gk);

        progressDialog = new ViewDialog(getActivity());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;



            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                Recy_recy_items itemname = caterogotyArraylist.get(position);


                Intent i = new Intent(getActivity(), Gk_list.class);
                i.putExtra("id",itemname.getGkId());
                i.putExtra("title",itemname.getCategroyName());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));

        getCategortList();
        return v;
    }




    public void  getCategortList(){


        String a = "{\"email\":\"";
        String b = "\",\"password\":\"";
        String c = "\"}";
        String url = "https://egknow.com/service-web/webservice.php?method=getCategoryList" ;
        Log.d("RanjeetUrlCheck", url);

        progressDialog.showDialog();

        Log.d("categoty", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("categorylist", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("categoryList");
                            for (int i = 0 ;i<jsonArray.length();i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String categorry_ID = jsonSubJson.getString("category_id");
                                String category_name = jsonSubJson.getString("category_name");
                                String category_icon = jsonSubJson.getString("category_icon");

                                caterogotyArraylist.add(new Recy_recy_items(categorry_ID,category_name, category_icon));
                            }
                            Gk_adapter adapter = new Gk_adapter(caterogotyArraylist, getActivity());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adapter);

                        } catch (Exception r) {
                            progressDialog.hideDialog();
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getActivity(), "Successfully Logined", Toast.LENGTH_SHORT).show();
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
