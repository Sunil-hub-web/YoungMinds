package com.egk.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
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

public class ViewGk extends AppCompatActivity implements Html.ImageGetter{

    TextView title, description, categoryname, date, head;
    String descriptionvalue,titlevalue,titlehead,catname;
    ImageView notification;
    private final static String TAG = "TestImageGetter";
    String source = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gk);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titlehead=getIntent().getStringExtra("catname");
//        descriptionvalue= getIntent().getStringExtra("description");
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
//        description.setText(removeHtml(descriptionvalue));
        categoryname.setText(removeHtml(catname));
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
}catch(Exception e){
    date.setVisibility(View.GONE);
}


        source = getIntent().getStringExtra("description");
        Spanned spanned = Html.fromHtml(source, this, null);
        description.setText(spanned);
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


    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
//        Drawable empty = getResources().getDrawable(R.drawable.app_icon);
//        d.addLevel(0, 0, empty);
//        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("rgvc", String.valueOf(e));
            } catch (MalformedURLException e) {
                Log.d("rgvc", String.valueOf(e));
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("rgvc", String.valueOf(e));
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = description.getText();
                description.setText(t);
            }
        }
    }


}
