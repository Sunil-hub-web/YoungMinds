package com.egk.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.egk.activites.LeaderBoard;
import com.egk.activites.QuestionAns_Activity;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;


public class EgkQuiz extends Fragment {

    SessionManager session;
    ViewDialog progressDialog;
    ArrayList<String> arrayquiz= new ArrayList<String>();
    TextView txt_myscore,score_value,next_session,session_value,date_time,date_time_value,timeValue,txt_leader,
    competationName,prizedate,date_val;
    NetworkImageView image;
    ImageLoader imageLoader;
    Context context;
    LinearLayout session_linear;
    RelativeLayout quizlayout;
    TextView txt_sessoin;
    String date,str,session_start_time="",session_start_date="",competition_id="",session_id;
    int Apitime,realtime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_egk_quiz, container, false);

        image=(NetworkImageView)v.findViewById(R.id.img_books);
        txt_myscore=(TextView)v.findViewById(R.id.txt_myscore);
        score_value=(TextView)v.findViewById(R.id.score_value);
        next_session=(TextView)v.findViewById(R.id.next_session);
        session_value=(TextView)v.findViewById(R.id.session_value);
        date_time=(TextView)v.findViewById(R.id.date_time);
        date_time_value=(TextView)v.findViewById(R.id.date_time_value);
        timeValue=(TextView)v.findViewById(R.id.timeValue);
        quizlayout=(RelativeLayout)v.findViewById(R.id.quizlayout);
        competationName=(TextView)v.findViewById(R.id.competationName);
        prizedate=(TextView)v.findViewById(R.id.prizedate);
        date_val=(TextView)v.findViewById(R.id.date_val);
        txt_leader=(TextView)v.findViewById(R.id.txt_leader);
        session_linear=(LinearLayout)v.findViewById(R.id.session_linear);
        imageLoader = AppSingleton.getInstance(getActivity()).getImageLoader();


        txt_sessoin=(TextView)v.findViewById(R.id.txt_sessoin);
        progressDialog = new ViewDialog(getActivity());


        date  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        session_start_date= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());



        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        str  = sdf.format(new Date());
        session_start_time=sdf.format(new Date());


        session = new SessionManager(getActivity());

        txt_leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent inttt=new Intent(getActivity(), LeaderBoard.class);
               inttt.putExtra("compId",competition_id);
               startActivity(inttt);


            }
        });


            session_linear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (CheckDates(date, session_start_date) && (checktimings(str, session_start_time))) {
                        Intent inttt = new Intent(getActivity(), QuestionAns_Activity.class);
                        inttt.putExtra("sessionId", session.getSessionId());
                        startActivity(inttt);

                    } else {
                        Toast.makeText(getActivity(), "There is No Session at this Time", Toast.LENGTH_SHORT).show();
                        session_linear.setEnabled(false);


                    }
                }
            });

        getCompetitionList();
        return  v;

    }


    public static boolean CheckDates(String date, String session_start_date) {

        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

        boolean b = false;

        try {
            if (dfDate.parse(date).before(dfDate.parse(session_start_date))) {
                b = false;  // If start date is before end date.

            }  else {
                b = true; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }

    private boolean checktimings(String str,String session_start_time ) {

        String pattern = "HH:mm:ss";
        SimpleDateFormat sdfd = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdfd.parse(str);
            Date date2 = sdfd.parse(session_start_time);

            if(date1.before(date2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }

    public  void  getCompetitionList(){

        progressDialog.showDialog();
        String a = "{\"u_id\":\"";
        String b = "\"}";

        String url = "https://egknow.com/Web_Service/web_service.php?method=CompetitionList&data="+a+session.getUserID()+b;

        Log.d("QuizzUrlCheck", url);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Questresponse", response.toString());
                        String REsult = response.toString();

                        try {
                            progressDialog.hideDialog();
                            JSONObject jsonObjMain = new JSONObject(REsult);
                            String statuse = jsonObjMain.getString("success");
                            if (statuse.equalsIgnoreCase("true")) {

                                JSONArray jsonArray = jsonObjMain.getJSONArray("Competitions");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonSubJson = jsonArray.getJSONObject(i);

                                    competition_id = jsonSubJson.getString("competition_id");
                                    String competition_name = jsonSubJson.getString("competition_name");
                                    String competition_date = jsonSubJson.getString("competition_date");
                                    String competition_img = jsonSubJson.getString("competition_img");
                                    String competition_desc = jsonSubJson.getString("competition_desc");

                                    session_id = jsonSubJson.getString("session_id");
                                    String session_name = jsonSubJson.getString("session_name");
                                    session_start_date = jsonSubJson.getString("session_start_date");
                                    session_start_time = jsonSubJson.getString("session_start_time");
                                    String session_run_time = jsonSubJson.getString("session_run_time");
                                    String session_mark_question = jsonSubJson.getString("session_mark_question");
                                    String uidd = jsonSubJson.getString("u_id");
                                    String total_mark = jsonSubJson.getString("total_mark");

                                    image.setImageUrl(competition_img, imageLoader);

                                    competationName.setText(competition_name);
                                    date_val.setText(competition_date);

                                    session.setUserID(uidd);
                                    session.setSessionId(session_id);
                                    session_value.setText(session_name);
                                    date_time_value.setText(session_start_date);
                                    timeValue.setText(session_start_time);
                                    score_value.setText(total_mark);

//                                    session_linear.setEnabled(true);

                                }

                            }
//                            else if(session_id.equalsIgnoreCase("")){
//
//                                    session_linear.setVisibility(View.GONE);
//                                    Toast.makeText(getActivity(), "There is No Session at this time", Toast.LENGTH_SHORT).show();
//                                }


                            else if (statuse.equalsIgnoreCase("false")) {
                                quizlayout.setVisibility(View.GONE);
                                txt_sessoin.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "There is No Competetion", Toast.LENGTH_SHORT).show();
                            }



                        } catch (Exception r) {
                            progressDialog.hideDialog();

                            Log.d("ExceptionError", "ranjeet Error" + r.toString());
                            Toast.makeText(getActivity(), "There is No Session", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                            progressDialog.hideDialog();
                Log.d("Ranjeet", "Error: " + error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

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

