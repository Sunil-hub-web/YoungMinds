package com.egk.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.egk.adapter.Home_Adapter;
import com.egk.adapter.Offer_Adapter;
import com.egk.egk.Egk_nav;
import com.egk.egk.Offer_recy_list;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.extra.Constants;
import com.egk.extra.RecyclerTouchListener;
import com.egk.extra.SessionManager;
import com.egk.extra.ViewDialog;
import com.egk.gettersetter.Recy_list_items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.egk.egk.R.id.screen_area;
import static com.egk.egk.R.id.session_value;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ViewDialog progressbar;
    SessionManager session;
    ArrayList<Recy_list_items> joblist = new ArrayList<>();


    int[] icons = {
            R.drawable.todays,
            R.drawable.monthlygk,
            R.drawable.gk,


            R.drawable.matchpoint,
            R.drawable.glossary,
            R.drawable.report,

            R.drawable.exampapers,
            R.drawable.upcoming_exam,
            R.drawable.basic_gk,

            R.drawable.quiz,
            R.drawable.offers,
            R.drawable.notification,

            R.drawable.recharge_point,
            R.drawable.transaction,
            R.drawable.topup,

    };
    String[] names = {

            "Today's",
            "Monthly GK",
            "Category GK",

            "Match Point",
            "Glossary",
            "Report",

            "Sample Papers",
            "Upcoming Exam",
            "Basic GK",

            "Quiz",
            "Offers",
            "Notification",

            "Recharge Point",
            "Transactions",
            "Top Up",

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        progressbar = new ViewDialog(getActivity());
        session = new SessionManager(getActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.rcy_lst);

        for (int i = 0; i < icons.length; i++) {
            joblist.add(new Recy_list_items(names[i], icons[i]));

        }
        Home_Adapter adapter = new Home_Adapter(joblist, HomeFragment.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            Fragment intent1 = null;

            @Override
            public void onClick(View view, int position) {
                Log.e("mukesh", "" + position);
                if (position == 0) {
                    if (session.getToday().equalsIgnoreCase("1") || (session.getToday().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText(" Today's");
                        Fragment i = new Todays();
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        getFragmentValue(i);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 1) {
                    if (session.getMonthlyGk().equalsIgnoreCase("1") || (session.getMonthlyGk().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Monthly GK");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment l = new MonthlyGk();
                        getFragmentValue(l);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }


                } else if (position == 2) {
                    if (session.getCategogryGK().equalsIgnoreCase("1") || (session.getCategogryGK().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Category GK");
                        Fragment i = new GkItemCategory();
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        getFragmentValue(i);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }


                } else if (position == 3) {
                    if (session.getMatchPoint().equalsIgnoreCase("1") || (session.getMatchPoint().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Match Point");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment k = new MatchPoint();
                        getFragmentValue(k);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 4) {
                    if (session.getGlossary().equalsIgnoreCase("1") || (session.getGlossary().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Glossary");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment l = new Glossary();
                        getFragmentValue(l);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }


                } else if (position == 5) {
                    if (session.getReports().equalsIgnoreCase("1") || (session.getReports().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Report");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment l = new Report();
                        getFragmentValue(l);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }


                } else if (position == 6) {
                    if (session.getSamplePaper().equalsIgnoreCase("1") || (session.getSamplePaper().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Sample Papers");
                        Fragment o = new PreviousTestPAper_Fragment();
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        getFragmentValue(o);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 9) {
                    if (session.getQuiz().equalsIgnoreCase("1") || (session.getQuiz().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Egk Quiz");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment o = new EgkQuiz();
                        getFragmentValue(o);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 10) {
                    Egk_nav.headtitle.setText("Offers");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment m = new My_Offers();
                    getFragmentValue(m);

                } else if (position == 11) {
                    Egk_nav.headtitle.setText("Notifications");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment n = new My_Notifications();
                    getFragmentValue(n);
                } else if (position == 13) {
                    Egk_nav.headtitle.setText("Transactions");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment j = new My_Transaction();
                    getFragmentValue(j);

                } else if (position == 14) {
                    Egk_nav.headtitle.setText("Top Up");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment l = new My_Topup();
                    getFragmentValue(l);

                } else if (position == 7) {
                    if (session.getUpcomingExam().equalsIgnoreCase("1") || (session.getUpcomingExam().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Upcoming Exam");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment m = new Upcoming_Exam();
                        getFragmentValue(m);
                    } else {
                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 8) {
                    if (session.getBasicGK().equalsIgnoreCase("1") || (session.getBasicGK().equalsIgnoreCase("0") && session.getSubcription().equalsIgnoreCase("active"))) {
                        Egk_nav.headtitle.setText("Basic GK");
                        Egk_nav.egk_logo.setVisibility(View.GONE);
                        Fragment n = new BasicGk();
                        getFragmentValue(n);
                    } else {

                        Toast.makeText(getActivity(), "Please subscribe package to see all modules", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 12) {
                    Egk_nav.headtitle.setText("Recharge Point");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment o = new Recharge_point();
                    getFragmentValue(o);
                }

                if (intent1 != null) {

                    getFragmentValue(intent1);
                }
            }

            private void getFragmentValue(Fragment intent1) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(screen_area, intent1);
                ft.commit();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }
        }));

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();

        getSubscriptionDetails();
    }

    private void getSubscriptionDetails() {
        progressbar.showDialog();
//        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://egknow.com/service-web/webservice.php?method=checkUserSubcription",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(SignUp_Activity.this, response, Toast.LENGTH_LONG).show();
                        progressbar.hideDialog();
                        Log.d("fvsDevbf", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("success").equals("true")) {


                                String subcription = jsonObject.getString("subcription");
                                String PackageID = jsonObject.getString("PackageName");
                                String remainig_day = jsonObject.getString("remainig_day");
                                String expire_day = jsonObject.getString("expire_day");
                                String Today = jsonObject.getString("Today");
                                String Monthly_Gk = jsonObject.getString("Monthly Gk");
                                String Categogry_GK = jsonObject.getString("Categogry GK");
                                String Match_Point = jsonObject.getString("Match Point");
                                String glossary = jsonObject.getString("Glossary");
                                String Reports = jsonObject.getString("Reports");
                                String SamplePaper = jsonObject.getString("Sample Paper");
                                String UpcomingExam = jsonObject.getString("Upcoming Exam");
                                String Basic_GK = jsonObject.getString("Basic GK");
                                String Quiz = jsonObject.getString("Quiz");

                                session.setSubcription(subcription);
                                session.setPackageID(PackageID);
                                session.setRemainigDay(remainig_day);
                                session.setExpireDay(expire_day);
                                session.setToday(Today);
                                session.setMonthlyGk(Monthly_Gk);
                                session.setCategogryGK(Categogry_GK);
                                session.setMatchPoint(Match_Point);
                                session.setGlossary(glossary);
                                session.setReports(Reports);
                                session.setSamplePaper(SamplePaper);
                                session.setUpcomingExam(UpcomingExam);
                                session.setBasicGK(Basic_GK);
                                session.setQuiz(Quiz);


                            } else {

                                Toast.makeText(getActivity(), "" + jsonObject.getString("err_msg"), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                            progressbar.hideDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressbar.hideDialog();
//                        Toast.makeText(SignUp_Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                            Toast.makeText(getActivity(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                            // ...
                        } else {
                            NetworkResponse networkResponse = error.networkResponse;
                            if (networkResponse != null && networkResponse.data != null) {
                                try {
                                    String jError = new String(networkResponse.data);
                                    JSONObject jsonError = new JSONObject(jError);
                                    Log.d("successresponceVolley", "" + jsonError);

//                                    String data = jsonError.getString("msg");
//                                    if (data.equalsIgnoreCase("Please Upload Document")) {
//                                        Intent i = new Intent(getApplicationContext(), UploadAadhar.class);
//                                        startActivity(i);
//                                        finish();
//                                    } else {
//                                        Toast.makeText(Login.this, data, Toast.LENGTH_SHORT).show();
//                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("successresponceVolley", "" + e);
                                }

                                // Print Error!
                            }
                        }
                    }
                }) {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", session.getUserID());
                Log.d("fvsDevbf", "" + params);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }


}
