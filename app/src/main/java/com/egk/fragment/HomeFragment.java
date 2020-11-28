package com.egk.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.egk.adapter.Home_Adapter;
import com.egk.egk.Egk_nav;
import com.egk.egk.R;
import com.egk.extra.RecyclerTouchListener;
import com.egk.gettersetter.Recy_list_items;

import java.util.ArrayList;

import static com.egk.egk.R.id.screen_area;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

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
                    Egk_nav.headtitle.setText(" Today's");
                    Fragment i = new Todays();
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    getFragmentValue(i);
                }else if (position == 1) {
                    Egk_nav.headtitle.setText("Monthly GK");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment l = new MonthlyGk();
                    getFragmentValue(l);

                }
                else if (position == 2) {
                    Egk_nav.headtitle.setText("Category GK");
                    Fragment i = new GkItemCategory();
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    getFragmentValue(i);

                }
               else if (position == 3) {
                    Egk_nav.headtitle.setText("Match Point");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment k = new MatchPoint();
                    getFragmentValue(k);
                }else if (position == 4) {
                    Egk_nav.headtitle.setText("Glossary");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment l = new Glossary();
                    getFragmentValue(l);

                }
                else if (position == 5) {
                    Egk_nav.headtitle.setText("Report");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment l = new Report();
                    getFragmentValue(l);

                } else if (position == 6) {
                    Egk_nav.headtitle.setText("Sample Papers");
                    Fragment o = new PreviousTestPAper_Fragment();
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    getFragmentValue(o);
                }
                else if(position == 9){
                    Egk_nav.headtitle.setText("Egk Quiz");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment o = new EgkQuiz();
                    getFragmentValue(o);
                }
                else if (position == 10) {
                    Egk_nav.headtitle.setText("Offers");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment m = new My_Offers();
                    getFragmentValue(m);

                }
                else if (position == 11) {
                    Egk_nav.headtitle.setText("Notifications");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment n = new My_Notifications();
                    getFragmentValue(n);
                }else if (position == 13) {
                    Egk_nav.headtitle.setText("Transactions");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment j = new My_Transaction();
                    getFragmentValue(j);

                }
                else if (position == 14) {
                    Egk_nav.headtitle.setText("Top Up");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment l = new My_Topup();
                    getFragmentValue(l);

                }
                else if(position ==7){
                    Egk_nav.headtitle.setText("Upcoming Exam");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment m = new Upcoming_Exam();
                    getFragmentValue(m);
                }

                else if(position ==8){
                    Egk_nav.headtitle.setText("Basic GK");
                    Egk_nav.egk_logo.setVisibility(View.GONE);
                    Fragment n = new BasicGk();
                    getFragmentValue(n);
                }
                else if(position ==12){
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


}
