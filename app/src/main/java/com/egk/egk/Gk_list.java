package com.egk.egk;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.egk.activites.ViewGk;
import com.egk.adapter.GktotalAdapter;
import com.egk.adapter.PopularAdapter;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.GkGetSet;

public class Gk_list extends AppCompatActivity implements PopularAdapter.ContactsAdapterListener  {
    RecyclerView recyclerView;
    ViewDialog progressDialog;
    ImageView gk_backicon;
    TextView start, end;
    String stoen, id ;
    Date stdt, endt;
    ArrayList<GkGetSet> caterogotyArraylist = new ArrayList<GkGetSet>();
    private RecyclerView.LayoutManager layoutManager;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageView go;
//    public static String ;
    PopularAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

      getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_gk_list);

        progressDialog = new ViewDialog(this);

        gk_backicon=(ImageView)findViewById(R.id.gk_backicon);
        recyclerView = (RecyclerView)findViewById(R.id.rcy_gk_list);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);

        go = (ImageView) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(start.getText().toString().equalsIgnoreCase("Start Date") || end.getText().toString().equalsIgnoreCase("End Date")){
                    Toast.makeText(Gk_list.this, "Select both the dates", Toast.LENGTH_SHORT).show();
                }else {

                    getFilteredlist(id);
                }
//                adapter.getFilter().filter(stdt);

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoen = "start";

                start.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(Gk_list.this,R.style.MyTimePicker,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
//                                fragHome_Date.setText(dayOfMonth + "-"
//                                        + (monthOfYear + 1) + "-" + year);
                                String d = dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year;
                                start.setText(d);

                                String string = d;
                                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                                Date dt = null;
                                try {
                                    dt = format.parse(string);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                stdt = dt;

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker();
                dpd.show();

            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoen = "end";

                end.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(Gk_list.this,R.style.MyTimePicker,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
//                                fragHome_Date.setText(dayOfMonth + "-"
//                                        + (monthOfYear + 1) + "-" + year);
                                String d = dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year;
                                end.setText(d);String string = d;
                                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                                Date dt = null;
                                try {
                                    dt = format.parse(string);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                endt = dt;

                            }
                        }, mYear, mMonth, mDay);

                dpd.show();

            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(removeHtml(getIntent().getStringExtra("title")));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;



            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                GkGetSet itemname = caterogotyArraylist.get(position);


                Intent i = new Intent(getApplicationContext(), ViewGk.class);
                i.putExtra("title",itemname.getGk_title());
                i.putExtra("description",itemname.getGk_desc());
                i.putExtra("catname",itemname.getCategory_name());
                i.putExtra("date",itemname.getGk_date());
                startActivity(i);






            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));

        recyclerView.setHasFixedSize(true);

        adapter = new PopularAdapter(Gk_list.this,caterogotyArraylist, this);

//                                layoutManager = new LinearLayoutManager(PopularProduct.this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        id = getIntent().getStringExtra("id");
        getGkLsit(getIntent().getStringExtra("id"));
    }


    public void getGkLsit(String id){

        String a = "{\"category_id\":\"";
//        String b = "\",\"password\":\"";
        String c = "\"}";
        String url = "https://egknow.com/service-web/webservice.php?method=getGkList&data="+a+id+c;

        progressDialog.showDialog();
//        http://egknow.com/service-web/webservice.php?method=getGkList&data={"category_id":""}
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

                            JSONArray jsonArray = jsonObjMain.getJSONArray("gkList");
                            for (int i = 0 ;i<jsonArray.length();i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String gk_id = jsonSubJson.getString("gk_id");
                                String gk_desc = jsonSubJson.getString("gk_desc");
                                String gk_title = jsonSubJson.getString("gk_title");
                                String category_name = jsonSubJson.getString("category_name");
                                String gk_date = jsonSubJson.getString("gk_date");


                                caterogotyArraylist.add(new GkGetSet(gk_id, category_name, gk_date, gk_title, gk_desc));
                            }

//                            adapter.notifyDataSetChanged();
                            GktotalAdapter adapter = new GktotalAdapter(caterogotyArraylist);
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


    public void getFilteredlist(String id){

        String a = "{\"category_id\":\"";
//        String b = "\",\"password\":\"";
        String c = "\"}";
        String url = "https://egknow.com/service-web/webservice.php?method=getGkList&data="+a+id+c;

        progressDialog.showDialog();
//        http://egknow.com/service-web/webservice.php?method=getGkList&data={"category_id":""}
        Log.d("Receive", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Ranjeetregister", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        caterogotyArraylist.clear();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("gkList");
                            for (int i = 0 ;i<jsonArray.length();i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String gk_id = jsonSubJson.getString("gk_id");
                                String gk_desc = jsonSubJson.getString("gk_desc");
                                String gk_title = jsonSubJson.getString("gk_title");
                                String category_name = jsonSubJson.getString("category_name");
                                String gk_date = jsonSubJson.getString("gk_date");

                                String string = gk_date;
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                Date d = null;
                                try {
                                    d = format.parse(string);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Log.d("dfgthggA", String.valueOf(d));
                                Log.d("dfgthggB", String.valueOf(stdt));
                                Log.d("dfgthggC", String.valueOf(endt));
                                if(d.equals(stdt) || d.equals(endt)){

                                }
                                if (d.after(stdt) && d.before(endt)) {

                                    Log.d("tghyj", gk_title);
                                    caterogotyArraylist.add(new GkGetSet(gk_id, category_name, gk_date, gk_title, gk_desc));
                                }else if(d.equals(stdt) || d.equals(endt)){
                                    caterogotyArraylist.add(new GkGetSet(gk_id, category_name, gk_date, gk_title, gk_desc));
                                }else{

                                }
                            }

//                            adapter.notifyDataSetChanged();
                            GktotalAdapter adapter = new GktotalAdapter(caterogotyArraylist);
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

    @Override
    public void onContactSelected(GkGetSet contact) {
    }


    private String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>"," ");
        html = html.replaceAll("<(.*?)\\\n"," ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&#039;s"," ");
        html = html.replaceAll("&amp;","&");
        html = html.replaceAll("&#39;","'");
        html = html.replaceAll("&nbsp;","");
        html = html.replaceAll("&amp;"," & ");
        html = html.replaceAll("&rsquo;s"," ");
        html = html.replaceAll("&rsquo;"," ");
        html = html.replaceAll("&ldquo;"," ");
        html = html.replaceAll("&rdquo;"," ");
        html = html.replaceAll("&quot;;","");
        html = html.replaceAll("&quot;","");
        html = html.replaceAll("&lsquo;","");



        return html;
    }
}
