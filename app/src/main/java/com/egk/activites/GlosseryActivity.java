package com.egk.activites;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GlosseryActivity extends AppCompatActivity implements Html.ImageGetter{
RecyclerView recyclerView;
    ViewDialog progressDialog;
    String catid;
    ArrayList<Glosserytittlegetset> glosserytittle= new ArrayList<Glosserytittlegetset>();
    Dialog dialogCoupon;
    String gltittle,descrtipi,name;
    ImageView gk_backicon;
    TextView title;
    private final static String TAG = "TestImageGetter";
    String source = "";
    TextView txt_tittle,txt_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossery);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.multiple_glossery_recycle);
        progressDialog=new ViewDialog(this);
        gk_backicon=(ImageView)findViewById(R.id.gk_backicon);
        title=(TextView)findViewById(R.id.title);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        catid=getIntent().getStringExtra("id");
        title.setText(removeHtml(getIntent().getStringExtra("name")));
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
        String url = "https://egknow.com/service-web/webservice.php?method=getGllosory&data="+a+catid+ b;

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


        txt_tittle=(TextView)dialogCoupon.findViewById(R.id.txt_tittle);
        txt_desc=(TextView)dialogCoupon.findViewById(R.id.txt_desc);

        txt_tittle.setText(removeHtml(gltittle));
//        txt_desc.setText(removeHtml(descrtipi));
//        source = getIntent().getStringExtra("description");
        Spanned spanned = Html.fromHtml(descrtipi, this, null);
        txt_desc.setText(spanned);

        dialogCoupon.show();
    }



    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
//        Drawable empty = getResources().getDrawable(R.drawable.app_icon);
//        d.addLevel(0, 0, empty);
//        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("rgvc", String.valueOf(e));
            } catch (MalformedURLException e) {
                Log.d("rgvc", String.valueOf(e));
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("rgvc", String.valueOf(e));
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = txt_desc.getText();
                txt_desc.setText(t);
            }
        }
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
        html = html.replaceAll("&nbsp;","");

        return html;
    }

}
