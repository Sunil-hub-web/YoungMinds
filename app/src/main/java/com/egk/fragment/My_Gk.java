package com.egk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.egk.activites.GkCategory;
import com.egk.activites.ViewGk;
import com.egk.adapter.GktotalAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.GkGetSet;


public class My_Gk extends Fragment {
    ArrayList<GkGetSet> caterogotyArraylist = new ArrayList<GkGetSet>();

    RecyclerView recyclerView;

    ViewDialog progressDialog;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_my__gk, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcy_gk);

        RelativeLayout selectcat = (RelativeLayout) v.findViewById(R.id. selectcat);

        selectcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GkCategory.class);
                startActivity(i);
            }
        });

        progressDialog = new ViewDialog(getActivity());



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;

            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                GkGetSet itemname = caterogotyArraylist.get(position);


                    Intent i = new Intent(getActivity(), ViewGk.class);
                    i.putExtra("title",itemname.getGk_title());
                    i.putExtra("description",itemname.getGk_desc());
                    i.putExtra("catname",itemname.getCategory_name());
                    i.putExtra("date",itemname.getGk_date());
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


       String url = "https://egknow.com/service-web/webservice.php?method=getCurrentGkList" ;
       Log.d("RanjeetUrlCheck", url);

       progressDialog.showDialog();



       final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
       JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       Log.d("gkresponse", response.toString());
                       String REsult = response.toString();
                       //    pDialog.dismiss();
                       progressDialog.hideDialog();

                       try {
                           JSONObject jsonObjMain = new JSONObject(REsult);
                           String statuse = jsonObjMain.getString("success");

                           JSONArray jsonArray = jsonObjMain.getJSONArray("currentGkList");
                           for (int i = 0 ;i<jsonArray.length();i++) {

                               JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                               String gk_id = jsonSubJson.getString("gk_id");
                               String category_name = jsonSubJson.getString("category_name");
                               String gk_date = jsonSubJson.getString("gk_date");
                               String gk_title = jsonSubJson.getString("gk_title");
                               String gk_desc = jsonSubJson.getString("gk_desc");

                               caterogotyArraylist.add(new GkGetSet(gk_id, category_name, gk_date, gk_title, gk_desc));
                           }
                           GktotalAdapter adapter = new GktotalAdapter(caterogotyArraylist);
                           recyclerView.setHasFixedSize(true);
                           layoutManager = new LinearLayoutManager(getActivity());
                           recyclerView.setLayoutManager(layoutManager);
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
