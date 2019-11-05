package com.egk.activites;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import com.egk.adapter.Gk_adapter;
import com.egk.egk.Gk_list;
import com.egk.egk.R;
import com.egk.egk.Recy_recy_items;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;

public class GkCategory extends AppCompatActivity {

    ArrayList<Recy_recy_items> caterogotyArraylist = new ArrayList<>();

    RecyclerView recyclerView;
    String[] name = {
            "GK",
            "Transactions",
            "Profile",
            "Topup",
            "Offers",
            "Notifications"
    };

    ViewDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gk_category);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//
//        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//
//        recyclerView = (RecyclerView)findViewById(R.id.rcy_gk);
//
//        progressDialog = new ViewDialog(this);
//
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            Fragment intent1 = null;
//
//
//
//            @Override
//            public void onClick(View view, int position) {
//                Log.e("mukesh", "" + position);
//                Recy_recy_items itemname = caterogotyArraylist.get(position);
//
//
//                Intent i = new Intent(getApplicationContext(), Gk_list.class);
//                i.putExtra("id",itemname.getGkId());
//                i.putExtra("title",itemname.getCategroyName());
//                startActivity(i);
//
//
//
//
//
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "Long press on position :" + position,
//                        Toast.LENGTH_LONG).show();
//
//            }
//        }));
//
//        getCategortList();
//    }
//
//    public void  getCategortList(){
//
//
//        String a = "{\"email\":\"";
//        String b = "\",\"password\":\"";
//        String c = "\"}";
//        String url = "https://egknow.com/service-web/webservice.php?method=getCategoryList" ;
//        Log.d("RanjeetUrlCheck", url);
//
//        progressDialog.showDialog();
//
//        Log.d("Receive", url);
//
//        final RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("Ranjeetregister", response.toString());
//                        String REsult = response.toString();
//                        //    pDialog.dismiss();
//                        progressDialog.hideDialog();
//
//                        try {
//                            JSONObject jsonObjMain = new JSONObject(REsult);
//                            String statuse = jsonObjMain.getString("success");
//
//                            JSONArray jsonArray = jsonObjMain.getJSONArray("categoryList");
//                            for (int i = 0 ;i<jsonArray.length();i++) {
//
//                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
//                                String categorry_ID = jsonSubJson.getString("category_id");
//                                String category_name = jsonSubJson.getString("category_name");
//                                String category_icon = jsonSubJson.getString("category_icon");
//
//                                caterogotyArraylist.add(new Recy_recy_items(categorry_ID,category_name, category_icon));
//                            }
//                            Gk_adapter adapter = new Gk_adapter(caterogotyArraylist, GkCategory.this);
//                            recyclerView.setHasFixedSize(true);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            recyclerView.setAdapter(adapter);
//
//                        } catch (Exception r) {
//                            progressDialog.hideDialog();
//                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
//                            Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.hideDialog();
//                Log.d("Ranjeet", "Error: " + error.getMessage());
//                // hide the progress dialog
//
////               getValues();
//                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
//                    // ...
//                }
//            }
//        });
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                50000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);
//    }
}}
