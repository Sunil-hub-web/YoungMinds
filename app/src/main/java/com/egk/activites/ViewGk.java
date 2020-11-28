package com.egk.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.fragment.My_Notifications;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewGk extends AppCompatActivity {

    TextView title, categoryname, date, head;
    String descriptionvalue, titlevalue, titlehead, catname;
    ImageView notification;
    WebView description;
    private final static String TAG = "TestImageGetter";
    String source = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gk);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titlehead = getIntent().getStringExtra("catname");
//        descriptionvalue= getIntent().getStringExtra("description");
        titlevalue = getIntent().getStringExtra("title");
        catname = getIntent().getStringExtra("catname");

        notification = (ImageView) findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                My_Notifications fragment = new My_Notifications();
                fm.beginTransaction().replace(R.id.relativemain, fragment).commit();

            }
        });


        ImageView back = (ImageView) findViewById(R.id.gk_backicon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Log.d("ranjeetTesting_title", "" + titlehead);
        Log.d("ranjeetTesting_head", "" + titlevalue);
        Log.d("ranjeetTesting_cat", "" + catname);

        head = (TextView) findViewById(R.id.head);
        title = (TextView) findViewById(R.id.title);
        description = (WebView) findViewById(R.id.description);
        categoryname = (TextView) findViewById(R.id.categoryname);
        date = (TextView) findViewById(R.id.date);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            title.setText(Html.fromHtml(titlevalue, Html.FROM_HTML_MODE_COMPACT));
            head.setText(Html.fromHtml(titlehead, Html.FROM_HTML_MODE_COMPACT));
//        description.setText(removeHtml(descriptionvalue));
            categoryname.setText(Html.fromHtml(catname, Html.FROM_HTML_MODE_COMPACT));

        } else {

            title.setText(Html.fromHtml(titlevalue));
            head.setText(Html.fromHtml(titlehead));
//        description.setText(removeHtml(descriptionvalue));
            categoryname.setText(Html.fromHtml(catname));

        }


        try {
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

            date.setText("Date : " + dt);
        } catch (Exception e) {
            date.setVisibility(View.GONE);
        }


        source = getIntent().getStringExtra("description");
        description.getSettings().setJavaScriptEnabled(true);
        description.loadDataWithBaseURL(null, source, "text/html", "utf-8", null);;
    }


}
