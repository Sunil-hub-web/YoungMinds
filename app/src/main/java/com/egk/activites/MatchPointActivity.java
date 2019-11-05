package com.egk.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.egk.egk.R;

public class MatchPointActivity extends AppCompatActivity {
String id,descptin,datee;
TextView txt_desc,txt_date;
ImageView gk_backicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_point);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txt_desc=(TextView)findViewById(R.id.txt_desc);
        txt_date=(TextView)findViewById(R.id.txt_date);
        gk_backicon=(ImageView)findViewById(R.id.gk_backicon);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        id=getIntent().getStringExtra("id");
        descptin=getIntent().getStringExtra("desc");
        datee=getIntent().getStringExtra("date");

        txt_desc.setText(removeHtml(descptin));
        txt_date.setText(removeHtml("Date : "+datee));
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
