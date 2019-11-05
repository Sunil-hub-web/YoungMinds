package com.egk.activites;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import com.egk.adapter.GlosseryTittleAdapter;
import com.egk.adapter.MatchPointAdapter;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.GlosseryGetSet;
import com.egk.gettersetter.Glosserytittlegetset;
import com.egk.gettersetter.MatchPointGetSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GlosseryActivity extends AppCompatActivity {
RecyclerView recyclerView;
    ViewDialog progressDialog;
    String catid;
    ArrayList<Glosserytittlegetset> glosserytittle= new ArrayList<Glosserytittlegetset>();
    Dialog dialogCoupon;
    String gltittle,descrtipi;
    ImageView gk_backicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossery);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.multiple_glossery_recycle);
        progressDialog=new ViewDialog(this);
        gk_backicon=(ImageView)findViewById(R.id.gk_backicon);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        catid=getIntent().getStringExtra("id");
        Log.d("fkjn",catid);

        getGlosseryItems();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Glosserytittlegetset gloseryy = glosserytittle.get(position);

                gltittle=gloseryy.getTitle();
                descrtipi=gloseryy.getDecription();

try {
    PopupBox();
}catch (Exception e){
    e.printStackTrace();
}


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



    }

    public void getGlosseryItems() {
        String a = "{\"Category_id\":\"";
        String b = "\"}";
        String url = "https://egknow.com/Web_Service/web_service.php?method=getGllosory&data="+a+catid+ b;

        progressDialog.showDialog();

        Log.d("gloss", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("glossrepo", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();
                        progressDialog.hideDialog();

                        try {
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("glossaryList");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String glossary_id = jsonSubJson.getString("glossary_id");
                                    String title = jsonSubJson.getString("title");
                                    String decription = jsonSubJson.getString("decription");

                                    glosserytittle.add(new Glosserytittlegetset(glossary_id, title,decription));
                                }

                                GlosseryTittleAdapter adapter = new GlosseryTittleAdapter(glosserytittle, getApplicationContext());
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapter);
                               }else if (statuse.equalsIgnoreCase("false")){
                                String message=jsonObjMain.getString("msg");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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

    public void PopupBox(){

        dialogCoupon= new Dialog(this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.glossery_popupbox);
        dialogCoupon.setCancelable(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCoupon.setCanceledOnTouchOutside(true);



     TextView txt_tittle,txt_desc;

        txt_tittle=(TextView)dialogCoupon.findViewById(R.id.txt_tittle);
        txt_desc=(TextView)dialogCoupon.findViewById(R.id.txt_desc);

        txt_tittle.setText(gltittle);
        txt_desc.setText(removeHtml(descrtipi));


        dialogCoupon.show();
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
        html = html.replaceAll("&nbs","");
        html = html.replaceAll("&am"," ");
        html = html.replaceAll("&rsquo;","");
        html = html.replaceAll("&lsquo;","");
        html = html.replaceAll("&ldquo;","");
        html = html.replaceAll("&rdquo;","");
        html = html.replaceAll("&ndash;","");
        html = html.replaceAll("&rsquo;s","");
        html = html.replaceAll("&hellip;","");
        html = html.replaceAll("&#039;"," ");
        html = html.replaceAll("&quot;"," ");
        html = html.replaceAll("<p>","");

        return html;
    }
}
