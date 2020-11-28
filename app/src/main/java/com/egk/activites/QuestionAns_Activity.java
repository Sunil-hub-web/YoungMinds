package com.egk.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.egk.egk.Egk_nav;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;

public class QuestionAns_Activity extends AppCompatActivity {
TextView txt_quest;
RadioButton radio_btn1,radio_btn2,radio_btn3,radio_btn4;
SessionManager sessionManager;
    RadioGroup radioGroup;
    Button submit;
    ViewDialog progressDialog;
    String selectquest;
    int number;
    String sesId,competitionsId;
    String date;
    String Time;
    String str;
    String question_id,question_ans1,question_ans2,question_ans3,question_ans4;
    String minn;
    private ShimmerFrameLayout mShimmerViewContainer;
    RelativeLayout viewContent,relative1;
    View includedLayout;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ans_);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txt_quest=(TextView)findViewById(R.id.txt_quest);
        radio_btn1=(RadioButton)findViewById(R.id.radio_btn1);
        radio_btn2=(RadioButton)findViewById(R.id.radio_btn2);
        radio_btn3=(RadioButton)findViewById(R.id.radio_btn3);
        radio_btn4=(RadioButton)findViewById(R.id.radio_btn4);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        submit=(Button)findViewById(R.id.submit);

         includedLayout = findViewById(R.id.icludeShimmer);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sessionManager = new SessionManager(this);
        progressDialog = new ViewDialog(this);
        date  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Time  = java.text.DateFormat.getTimeInstance().format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        str  = sdf.format(new Date());
        viewContent=(RelativeLayout)findViewById(R.id.viewContent);
        relative1=(RelativeLayout)findViewById(R.id.relative1);

//        minn= LocalTime.MIN.plusSeconds(120).format(DateTimeFormatter.ISO_LOCAL_TIME);
//        Log.d("mukkkkk",minn);

        sesId=getIntent().getStringExtra("sessionId");

        QuestAnswer("","");

//        addRadioButtons();
      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (radio_btn1.isChecked()) {
                  QuestAnswer(question_id,question_ans1);
                  radioGroup.invalidate();

              } else if (radio_btn2.isChecked()) {
                  QuestAnswer(question_id,question_ans2);
                  radioGroup.invalidate();
              } else if (radio_btn3.isChecked()) {
                  QuestAnswer(question_id,question_ans3);
                  radioGroup.invalidate();
              } else if (radio_btn4.isChecked()) {
                  QuestAnswer(question_id,question_ans4);
                  radioGroup.invalidate();
              }else {

                  Toast.makeText(QuestionAns_Activity.this, "Select Atleast 1 Answer", Toast.LENGTH_SHORT).show();


              }
          }


      });
    }


    public  void QuestAnswer(String questionId,String questAns){

        mShimmerViewContainer.startShimmer();


        String a = "{\"session_id\":\"";
        String b = "\", \"date\":\"";
        String c = "\", \"time\":\"";
        String d = "\", \"u_id\":\"";
        String e = "\", \"question_id\":\"";
        String f = "\", \"ans\":\"";
        String g = "\"}";

        String url = "https://egknow.com/Web_Service/web_service.php?method=session_star&data="+a+sesId+b+date+c+str+d+sessionManager.getUserID()
                +e+questionId+f+questAns+g;

        Log.d("QuestionAnswer", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("QuestionAnsres", response.toString());
                        String REsult = response.toString();
                        //    pDialog.dismiss();

                        try {

                            mShimmerViewContainer.stopShimmer();
                            viewContent.setVisibility(View.VISIBLE);
                            includedLayout.setVisibility(View.GONE);

                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("Session");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);
                                    String sesId = jsonSubJson.getString("session_id");
                                    String competitions_id = jsonSubJson.getString("competitions_id");
                                    question_id = jsonSubJson.getString("question_id");
                                    String question_name = jsonSubJson.getString("question_name");
                                    question_ans1 = jsonSubJson.getString("question_ans1");
                                     question_ans2 = jsonSubJson.getString("question_ans2");
                                    question_ans3 = jsonSubJson.getString("question_ans3");
                                    question_ans4 = jsonSubJson.getString("question_ans4");
                                    String session_run = jsonSubJson.getString("session_run");

                                    txt_quest.setText(question_name);
                                    radio_btn1.setText(question_ans1);
                                    radio_btn2.setText(question_ans2);
                                    radio_btn3.setText(question_ans3);
                                    radio_btn4.setText(question_ans4);
                                }

                            }
                             else  {
                                if(statuse.equalsIgnoreCase("completed")) {
                                    includedLayout.setVisibility(View.GONE);
                                    Toast.makeText(QuestionAns_Activity.this, "Quiz Completed" , Toast.LENGTH_SHORT).show();
                              Intent intentBack = new Intent(getApplicationContext(), Egk_nav.class);
                              startActivity(intentBack);
                              finish();
                                }

                                    if (statuse.equalsIgnoreCase("false")) {

                                        txt_quest.setVisibility(View.GONE);
                                        radioGroup.setVisibility(View.GONE);
                                        submit.setVisibility(View.GONE);
                                        Toast.makeText(QuestionAns_Activity.this, "There Is No Quiz For You ", Toast.LENGTH_SHORT).show();


                                }
                            }

                        } catch (Exception r) {

                            mShimmerViewContainer.stopShimmer();
                            viewContent.setVisibility(View.VISIBLE);
                            includedLayout.setVisibility(View.GONE);
                            Log.d("Ranjeetkumar", "ranjeet Error" + r.toString());
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Ranjeet", "Error: " + error.getMessage());


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

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
