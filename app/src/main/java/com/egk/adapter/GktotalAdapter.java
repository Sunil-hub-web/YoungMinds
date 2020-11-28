package com.egk.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.egk.egk.R;
import com.egk.gettersetter.GkGetSet;

public class GktotalAdapter extends RecyclerView.Adapter<GktotalAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<GkGetSet> accepetedJobLists;

    public GktotalAdapter(List<GkGetSet> accepetedJobLists) {
        this.accepetedJobLists = accepetedJobLists;
    }

    @Override
    public GktotalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.from(parent.getContext()).inflate(R.layout.total_gk_layout, parent, false);
        GktotalAdapter.MyViewHolder holder = new GktotalAdapter.MyViewHolder(view);
        return holder;
//        return View;
    }

    @Override
    public void onBindViewHolder(GktotalAdapter.MyViewHolder holder, int position) {

//        if(position%2==0) {
//            holder.itemView.setBackgroundColor(Color.WHITE);
//        }
//        else{
//            holder.itemView.setBackgroundColor(Color.LTGRAY);
//        }

        GkGetSet movie=accepetedJobLists.get(position);

//        Log.d("Accepte",movie.getName());

        holder.name.setText(removeHtml(movie.getGk_title()));
        holder.catname.setText(removeHtml(movie.getCategory_name()));

        String strCurrentDate = movie.getGk_date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        String dt = format.format(newDate);

        holder.date.setText("  -  "+dt);
    }


    @Override
    public int getItemCount() {
        return accepetedJobLists.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, catname, price, date, to;
        public ImageView complete, reject;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            catname = (TextView) itemView.findViewById(R.id.catname);
            date = (TextView) itemView.findViewById(R.id.date);
            view = (View) itemView.findViewById(R.id.view);


        }
    }
    private String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>"," ");
        html = html.replaceAll("<(.*?)\\\n"," ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&#039;s"," ");
        html = html.replaceAll("&amp;","&");
        html = html.replaceAll("&#39;","'");
        html = html.replaceAll("&nbsp;","");
        html = html.replaceAll("&#039;s","");

        return html;
    }



}
