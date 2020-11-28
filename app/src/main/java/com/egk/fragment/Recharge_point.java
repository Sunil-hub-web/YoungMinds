package com.egk.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.egk.adapter.RechargePointAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.RechargePointSetget;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Recharge_point extends Fragment {

    RecyclerView recyclerView;
    ArrayList<RechargePointSetget> rechrgeGet = new ArrayList<RechargePointSetget>();

    ViewDialog progressDialog;
    ImageView go;
    String catname, catId, currentyear;
    EditText category;
    TextView nodata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recharge_point, container, false);

        progressDialog = new ViewDialog(getActivity());

        nodata = (TextView) v.findViewById(R.id.nodata);
        recyclerView = (RecyclerView) v.findViewById(R.id.rechargepoint_recyclerview);


        category = (EditText) v.findViewById(R.id.category);
        go = (ImageView) v.findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getText().toString().length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Enter the Pincode", Toast.LENGTH_SHORT).show();
                } else {
                    getPatnerdetails(category.getText().toString());
                }
            }
        });


        return v;
    }


    public void getPatnerdetails(String pincode) {
        String a = "{\"pincode\":\"";
        String b = "\",\"category_id\":\"";
        String c = "\"}";

//        https://egknow.com/Web_Service/web_service.php?method=GetPartners&data={"pincode":"765100"}
        String url = "https://egknow.com/Web_Service/web_service.php?method=GetPartners&data=" + a + pincode + c;

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
                            rechrgeGet = new ArrayList<RechargePointSetget>();


                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");

                            JSONArray jsonArray = jsonObjMain.getJSONArray("partners");
                            if (jsonArray.length() == 0) {
                                nodata.setVisibility(View.VISIBLE);
                            } else {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String name = jsonSubJson.getString("name");
                                    String email = jsonSubJson.getString("email");
                                    String mobile = jsonSubJson.getString("mobile");
                                    String source = "";


                                    rechrgeGet.add(new RechargePointSetget(name, mobile, email, "address", "city", "pincode", "gender"));


                                }


                                RechargePointAdapter adapter = new RechargePointAdapter(rechrgeGet, getActivity());
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(adapter);


                                nodata.setVisibility(View.GONE);
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
