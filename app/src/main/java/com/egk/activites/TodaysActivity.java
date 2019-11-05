package com.egk.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.egk.egk.R;

import org.w3c.dom.Text;

public class TodaysActivity extends AppCompatActivity {
TextView catname;
String desc;
ImageView gk_backicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        catname=(TextView)findViewById(R.id.catname);
        gk_backicon=(ImageView)findViewById(R.id.gk_backicon);

        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        desc=getIntent().getStringExtra("description");
        catname.setText(removeHtml(desc));

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
