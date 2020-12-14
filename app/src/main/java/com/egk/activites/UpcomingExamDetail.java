package com.egk.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.egk.egk.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpcomingExamDetail extends AppCompatActivity {

    TextView txt_datt,Descriptions, txt_title;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_exam_detail);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView gk_backicon = findViewById(R.id.gk_backicon);
        gk_backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_datt=(TextView)findViewById(R.id.txt_datt);
        Descriptions=(TextView)findViewById(R.id.Description);
        webview=(WebView)findViewById(R.id.webview);

        String strCurrentDate = getIntent().getStringExtra("date");
        String description = getIntent().getStringExtra("title");

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


        Spanned htmlAsSpanned = Html.fromHtml(description); // used by TextView
        Spanned htmlAsSpanneddt = Html.fromHtml(dt); // used by TextView

//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {

        txt_datt.setText("Date : " + htmlAsSpanneddt);
        txt_title.setText(htmlAsSpanned);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadDataWithBaseURL(null, description, "text/html", "utf-8", null);

    }
}
