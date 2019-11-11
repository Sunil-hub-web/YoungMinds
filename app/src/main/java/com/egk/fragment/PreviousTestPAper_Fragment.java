package com.egk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.egk.activites.ViewActivity;
import com.egk.adapter.TestPaperAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.TestPeperGetterSetter;


public class PreviousTestPAper_Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<TestPeperGetterSetter> testPaperArary = new ArrayList<TestPeperGetterSetter>();
    ViewDialog progressDialog;
ImageView go;
String catname,catId,currentyear;
    Spinner year,category;
    TextView nodata;

    //year
    HashMap<String, String> hashyear = new HashMap<String, String>();
    List<String> yearcategory = new ArrayList<String>();
    ArrayAdapter<String> yeardataAdapter;
    //category
    HashMap<String, String> hashypaper = new HashMap<String, String>();
    List<String> papercategory = new ArrayList<String>();
    ArrayAdapter<String> paperdataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the viewquiz for this fragment
        View v= inflater.inflate(R.layout.fragment_previous_test_paper_, container, false);
        progressDialog = new ViewDialog(getActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.rcy_testPaper);

        nodata = (TextView) v.findViewById(R.id.nodata);
        year = (Spinner) v.findViewById(R.id.year);
        category = (Spinner) v.findViewById(R.id.category);
        go = (ImageView) v.findViewById(R.id.go);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;

            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                TestPeperGetterSetter itemname = testPaperArary.get(position);

                Intent i = new Intent(getActivity(), ViewActivity.class);
                i.putExtra("title",itemname.getTestPaperTitle());
                i.putExtra("description",itemname.getTestPaperDesc());
                i.putExtra("source",itemname.getSource());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

            }
        }));




        //filter




//year
        yearcategory.add("2010");
        yearcategory.add("2011");
        yearcategory.add("2012");
        yearcategory.add("2013");
        yearcategory.add("2014");
        yearcategory.add("2015");
        yearcategory.add("2016");
        yearcategory.add("2017");
        yearcategory.add("2018");
        yearcategory.add("2019");
        yearcategory.add("2020");

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              currentyear = year.getItemAtPosition(year.getSelectedItemPosition()).toString();
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearcategory.add(0, "Select Year");
        yeardataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, yearcategory);
        yeardataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(yeardataAdapter);

        //category
        Categories();

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catname= category.getItemAtPosition(category.getSelectedItemPosition()).toString();
                catId = hashypaper.get(catname);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

   if (currentyear=="Select Year"){
    Toast.makeText(getActivity(), "Select Year", Toast.LENGTH_SHORT).show();
  }else if (catname=="Select Category"){
    Toast.makeText(getActivity(), "Select category", Toast.LENGTH_SHORT).show();
  } else {
       nodata.setVisibility(View.GONE);
       getFilteredTestpaper();
   }
            }
        });




      getTestpaper();

    return v;}


    public void getTestpaper(){
        String a = "{\"year\":\"";
        String b = "\",\"category_id\":\"";
        String c = "\"}";

        String url = "https://egknow.com/service-web/webservice.php?method=getPastPaperList&data="+a+""+b+""+c;;

        progressDialog.showDialog();

        Log.d("exampapers", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("exampapersresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("pastPaperList");
                            for (int i = 0; i <0; i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String testPaperId = jsonSubJson.getString("past_paper_id");
                                String testPaperTitle = jsonSubJson.getString("past_paper_name");
                                String testPaperDesc = jsonSubJson.getString("past_paper_desc");
                                String source = "";



                                testPaperArary.add(new TestPeperGetterSetter(testPaperId, testPaperTitle, testPaperDesc, source));
                            }

                            TestPaperAdapter adapter = new TestPaperAdapter(testPaperArary,PreviousTestPAper_Fragment.this );
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

    public void getFilteredTestpaper(){
        String a = "{\"year\":\"";
        String b = "\",\"category_id\":\"";
        String c = "\"}";

        String url = "https://egknow.com/service-web/webservice.php?method=getPastPaperList&data="+a+currentyear+b+catId+c;;

        progressDialog.showDialog();

        Log.d("exampapers", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("exampapersresponse", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            testPaperArary.clear();



                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("pastPaperList");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                String testPaperId = jsonSubJson.getString("past_paper_id");
                                String testPaperTitle = jsonSubJson.getString("past_paper_name");
                                String testPaperDesc = jsonSubJson.getString("past_paper_desc");
                                String source = "";



                                testPaperArary.add(new TestPeperGetterSetter(testPaperId, testPaperTitle, testPaperDesc, source));

                            }

                            TestPaperAdapter adapter = new TestPaperAdapter(testPaperArary,PreviousTestPAper_Fragment.this );
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adapter);

                            if (testPaperArary.size()==0) {
                                nodata.setVisibility(View.VISIBLE);
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

    public void Categories() {

        String url = "https://egknow.com/Web_Service/web_service.php?method=getPaperCategory";

        Log.d("Category", url);

//        viewDialog.showDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("categoryresponse", response.toString());
                        String REsult = response.toString();

                        try {

                            papercategory.clear();
                            hashypaper.clear();
                            JSONObject jsoObject = new JSONObject(REsult);
                            String status = jsoObject.getString("success");

                            if (status.equalsIgnoreCase("true")) {


                                JSONArray jsonArrayMain = jsoObject.getJSONArray("test_paper_category");

                                for (int i = 0; i < jsonArrayMain.length(); i++) {

                                    JSONObject jsonObject = jsonArrayMain.getJSONObject(i);

                                    String catagory_id = jsonObject.getString("c_id");
                                    String categoty = jsonObject.getString("c_name");

                                    papercategory.add(categoty);
                                    hashypaper.put(categoty, catagory_id);

                                }
                                papercategory.add(0, "Select Category");
                                paperdataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, papercategory);
                                paperdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                category.setAdapter(paperdataAdapter);

                            }else {

//                                Toast.makeText(getActivity(),"Error in Category" , Toast.LENGTH_SHORT).show();

                            }


                        } catch (Exception e) {
                            Log.d("edghyt", "ranjeet Error" + e.toString());

                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("edghyt", "Error: " + error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity().getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                }


            }


        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        AppSingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq, url);

    }

}
