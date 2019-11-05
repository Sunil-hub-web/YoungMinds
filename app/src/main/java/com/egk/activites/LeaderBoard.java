package com.egk.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.egk.adapter.QuizAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.QuizGetSet;

public class LeaderBoard extends AppCompatActivity {
    ArrayList<QuizGetSet> quizlist = new ArrayList<>();
    RecyclerView recyclerView;
    String cpId;
    ViewDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_leader);
        cpId=getIntent().getStringExtra("compId");
        progressDialog = new ViewDialog(LeaderBoard.this);

        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        getLeaderBoard();

    }
    public  void getLeaderBoard(){

        String a = "{\"competitions_id\":\"";
        String c = "\"}";
        String url = " https://egknow.com/Web_Service/web_service.php?method=leadr_board&data="+a+cpId+c;

        progressDialog.showDialog();

        Log.d("Receive", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("competition_mark");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);

                                    String name = jsonSubJson.getString("u_name");
                                    String mark = jsonSubJson.getString("marks");
                                    String number = jsonSubJson.getString("rank");
                                    quizlist.add(new QuizGetSet(number,name,mark));

                                }

                                QuizAdapter adapter = new QuizAdapter(quizlist,LeaderBoard.this );
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapter);

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
