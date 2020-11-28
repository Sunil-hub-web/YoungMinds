package com.egk.egk;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.egk.activites.ViewGk;
import com.egk.adapter.BasicGkListAdapter;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.BasicGkListGetSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BasicGkList extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewDialog progressDialog;
    ImageView gk_backicon;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<BasicGkListGetSet> categorynewslist = new ArrayList<BasicGkListGetSet>();
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_basic_gk_list);

        progressDialog = new ViewDialog(this);
        title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));

        gk_backicon = (ImageView) findViewById(R.id.gk_backicon);
        recyclerView = (RecyclerView) findViewById(R.id.rcy_gk_list);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String id = getIntent().getStringExtra("id");

        getGkLsit(id);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;


            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                BasicGkListGetSet itemname = categorynewslist.get(position);


                Intent i = new Intent(getApplicationContext(), ViewGk.class);
                i.putExtra("title", itemname.getGk_title());
                i.putExtra("description", itemname.getGk_desc());
                i.putExtra("catname", itemname.getCategory_id());
//                i.putExtra("date",itemname.getGk_date());
                startActivity(i);


            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));


    }


    public void getGkLsit(String id) {

        String a = "{\"Category_id\":\"";
//        String b = "\",\"password\":\"";
        String c = "\"}";
        String url = "https://egknow.com/Web_Service/web_service.php?method=GetBasicGk&data=" + a + id + c;

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

                        // playerList.add

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("basic_gk");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String gk_id = jsonSubJson.getString("basicgk_id");
                                String gk_desc = jsonSubJson.getString("descrip");
                                String gk_title = jsonSubJson.getString("title");
                                String category_id = jsonSubJson.getString("category_id");

                                categorynewslist.add(new BasicGkListGetSet(gk_id, category_id, gk_title, gk_desc));

                            }

                            BasicGkListAdapter adapter = new BasicGkListAdapter(categorynewslist);
                            recyclerView.setHasFixedSize(true);

                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
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
