package com.egk.activites;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egk.adapter.MyAdapter;
import com.egk.egk.Login;
import com.egk.egk.R;
import com.egk.gettersetter.SliderGetSet;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GetStartedPage extends AppCompatActivity {
RelativeLayout getstarted;
    CirclePageIndicator indicator;
    public static ViewPager mPager;
    public static int currentPage = 0;
    public static int NUM_PAGES = 0;
    public List<SliderGetSet> urls = new ArrayList<SliderGetSet>();
     TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_page);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getstarted=(RelativeLayout)findViewById(R.id.getstarted);


        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        skip=(TextView) findViewById(R.id.skip);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Kozuka Gothic Pro H.otf");
        skip.setTypeface(typeface);

        init();

    }

    private void init() {


        urls.add(new SliderGetSet("1","jdj",R.drawable.s_one));
        urls.add(new SliderGetSet("1","jdj",R.drawable.s_two));
        urls.add(new SliderGetSet("1","jdj",R.drawable.s_three));

        mPager.setAdapter(new MyAdapter(getApplicationContext(), urls));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(3 * density);

        NUM_PAGES = urls.size();


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

}
