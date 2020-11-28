package com.egk.activites;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import com.egk.egk.R;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.ViewQuizGetSet;

public class ViewQuiz extends AppCompatActivity {
    ArrayList<ViewQuizGetSet> viewquizlist = new ArrayList<>();
    RecyclerView recyclerView;
    String uid;
    ViewDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz);
        recyclerView = (RecyclerView)findViewById(R.id.recyl_viewquiz);
        uid=getIntent().getStringExtra("id");
        progressDialog = new ViewDialog(ViewQuiz.this);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getsessionData();

        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            Fragment intent1 = null;
//            @Override
//            public void onClick(View view, int position) {
//                Log.e("mukesh", "" + position);
//                ViewQuizGetSet viewquizget = viewquizlist.get(position);
//
//
//                Intent i = new Intent(getApplicationContext(), QuestionAns_Activity.class);
////                i.putExtra("sessionId",viewquizget.getSes_id());
//                startActivity(i);
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




    }

//    public void   getsessionData(){
//
//        String a = "{\"competition_id\":\"";
//        String b = "\"}";
//    String url = "http://egknow.com/Web_Service/web_service.php?method=session&data="+a+uid+b ;
//
//    Log.d("ViewQuiztUrlCheck", url);
//
//    progressDialog.showDialog();
//
//    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.d("viewquizresponse", response.toString());
//                    String REsult = response.toString();
//
//                    progressDialog.hideDialog();
//
//                    try {
//                        JSONObject jsonObjMain = new JSONObject(REsult);
//                        String statuse = jsonObjMain.getString("success");
//                        if(statuse.equals("true")) {
//                            JSONArray jsonArray = jsonObjMain.getJSONArray("Session");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                JSONObject jsonSubJson = jsonArray.getJSONObject(i);
//                                String ses_id = jsonSubJson.getString("session_id");
//                                String ses_name = jsonSubJson.getString("session_name");
//                                String startDate = jsonSubJson.getString("session_start_date");
//                                String startTime = jsonSubJson.getString("session_start_time");
//                                String timerQuest = jsonSubJson.getString("session_timer_question");
//                                String markQuest = jsonSubJson.getString("session_mark_question");
//
//                                viewquizlist.add(new ViewQuizGetSet(ses_id,ses_name,startDate,startTime,timerQuest,markQuest));
//
//                            }
//
//                            ViewQuizAdapter adapter = new ViewQuizAdapter(viewquizlist, ViewQuiz.this);
//                            recyclerView.setHasFixedSize(true);
//                            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//                            recyclerView.setAdapter(adapter);
//
//                        }
//
//                        else{
//
//                            Toast.makeText(ViewQuiz.this, "This Time No Session Available", Toast.LENGTH_SHORT).show();
//
//                        }
//                    } catch (Exception r) {
//                        progressDialog.hideDialog();
//                        Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
//                        Toast.makeText(getApplicationContext(), "Successfully Logined", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            progressDialog.hideDialog();
//            Log.d("Ranjeet", "Error: " + error.getMessage());
//
//            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    });
//    jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//            50000,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//    AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq, url);
//}

}
