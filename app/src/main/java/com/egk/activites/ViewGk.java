package com.egk.activites;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.egk.egk.Egk_nav;
import com.egk.egk.R;
import com.egk.fragment.My_Notifications;

public class ViewGk extends AppCompatActivity {

    TextView title, description, categoryname, date, head;
    String descriptionvalue,titlevalue,titlehead,catname;
    ImageView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gk);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titlehead=getIntent().getStringExtra("catname");
        descriptionvalue= getIntent().getStringExtra("description");
        titlevalue=getIntent().getStringExtra("title");
        catname=getIntent().getStringExtra("catname");

        notification= (ImageView) findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                My_Notifications fragment= new My_Notifications();
                fm.beginTransaction().replace(R.id.relativemain,fragment).commit();

            }
        });


        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Log.d("ranjeetTesting","runnning  n comming n going");

        head = (TextView) findViewById(R.id.head);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        categoryname = (TextView) findViewById(R.id.categoryname);
        date = (TextView) findViewById(R.id.date);


        title.setText(removeHtml(titlevalue));
        head.setText(removeHtml(titlehead));
        description.setText(removeHtml(descriptionvalue));
        categoryname.setText(removeHtml(catname));

        String strCurrentDate = getIntent().getStringExtra("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        String dt = format.format(newDate);

//        holder.date.setText("  -  "+dt);

        date.setText("Date : "+dt);
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
        html = html.replaceAll("&rsquo;s"," ");
        html = html.replaceAll("&rsquo;"," ");
        html = html.replaceAll("&ldquo;"," ");
        html = html.replaceAll("&rdquo;"," ");
        html = html.replaceAll("&quot;;","");
        html = html.replaceAll("&quot;","");
        html = html.replaceAll("&lsquo;","");



        return html;
    }


}
