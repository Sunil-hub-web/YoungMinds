package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.GkGetSet;
import com.egk.gettersetter.TodaysGetSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodaysAdapter extends RecyclerView.Adapter<TodaysAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<TodaysGetSet> todaysGetSets;

    public TodaysAdapter(List<TodaysGetSet> todaysGetSets,Context context) {
        this.todaysGetSets = todaysGetSets;
        this.context=context;
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

        TodaysGetSet movie=todaysGetSets.get(i);

//        Log.d("Accepte",movie.getName());

        holder.catname.setText(removeHtml(movie.getDescription()));

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

        holder.date.setText("  -  "+dt);

    }

    @Override
    public int getItemCount() {
        return todaysGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView catname, date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catname = (TextView) itemView.findViewById(R.id.catname);
            date = (TextView) itemView.findViewById(R.id.date);
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
