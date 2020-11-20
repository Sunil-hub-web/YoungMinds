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
import com.egk.adapter.UpcomingAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.UpcomingExam;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Upcoming_Exam extends Fragment {
    RecyclerView recyclerView;
    ArrayList<UpcomingExam> upcomingGk = new ArrayList<UpcomingExam>();

    ViewDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_upcoming__exam, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.upcomingExam_recycle);
        progressDialog=new ViewDialog(getActivity());
        getupComingExam();
        return v;

    }

    public void  getupComingExam() {

        String url = "https://egknow.com/Web_Service/web_service.php?method=GetUpcommingExam";

        progressDialog.showDialog();

        Log.d("matchpoint", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("matchpointrepo", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("All_upcom_res");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String match_point_id = jsonSubJson.getString("up_comming_exam_id");
                                    String match_date = jsonSubJson.getString("date");
                                    String description = jsonSubJson.getString("title");
                                    upcomingGk.add(new UpcomingExam(match_date,description));
                                }
//                                Collections.reverse(matchPointGet);
                                UpcomingAdapter adapter = new UpcomingAdapter(upcomingGk, getActivity());
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
