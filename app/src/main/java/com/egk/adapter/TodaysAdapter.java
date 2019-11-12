package com.egk.adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.egk.egk.R;
import com.egk.gettersetter.TodaysGetSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodaysAdapter extends RecyclerView.Adapter<TodaysAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<TodaysGetSet> todaysGetSets;
    private final static String TAG = "TestImageGetter";
    TextView catname, date;
    String source = "";

    public TodaysAdapter(List<TodaysGetSet> todaysGetSets, Context context) {
        this.todaysGetSets = todaysGetSets;
        this.context = context;

    }

    @NonNull
    @Override
    public TodaysAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.todays_layout, viewGroup, false);
        TodaysAdapter.MyViewHolder holder = new TodaysAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodaysAdapter.MyViewHolder holder, int i) {

        TodaysGetSet movie = todaysGetSets.get(i);

        source=movie.getDescription();
        catname.setText(Html.fromHtml(source, imgGetter, null));

        String strCurrentDate = movie.getTodays_date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        String dt = format.format(newDate);

        date.setText("  -  " + dt);

    }


    @Override
    public int getItemCount() {
        return todaysGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname = (TextView) itemView.findViewById(R.id.catname);
            date = (TextView) itemView.findViewById(R.id.date);
        }


    }

    private String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>", " ");
        html = html.replaceAll("<(.*?)\\\n", " ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&#039;s", " ");
        html = html.replaceAll("&amp;", "&");
        html = html.replaceAll("&#39;", "'");
        html = html.replaceAll("&nbsp;", "");
        html = html.replaceAll("&amp;", " & ");
        html = html.replaceAll("&nbs", "");
        html = html.replaceAll("&am", " ");
        html = html.replaceAll("&rsquo;", "");
        html = html.replaceAll("&lsquo;", "");
        html = html.replaceAll("&ldquo;", "");
        html = html.replaceAll("&rdquo;", "");
        html = html.replaceAll("&ndash;", "");
        html = html.replaceAll("&rsquo;s", "");
        html = html.replaceAll("&hellip;", "");
        html = html.replaceAll("&quot;", "");

        return html;
    }

    private Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            LevelListDrawable d = new LevelListDrawable();
//            Drawable empty = context.getResources().getDrawable(R.drawable.app_icon);
//            d.addLevel(0, 0, empty);
//            d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

            return d;
        }

    };


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
                CharSequence t = catname.getText();
                catname.setText(t);
            }
        }
    }

}
