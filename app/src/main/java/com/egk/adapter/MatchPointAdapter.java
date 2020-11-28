package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.MatchPointGetSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MatchPointAdapter extends RecyclerView.Adapter<MatchPointAdapter.MyViewHolder>  {
    private ArrayList<MatchPointGetSet> matchPointGetSets;
    Context context;
    public  MatchPointAdapter(ArrayList<MatchPointGetSet> matchPointGetSets, Context context) {
        this.matchPointGetSets = matchPointGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.match_point_layout,viewGroup,false);
        return new MatchPointAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final MatchPointGetSet My_list =matchPointGetSets.get(i);

        String strCurrentDate = My_list.getMatch_date();
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

        myViewHolder.txt_datt.setText("Date : "+dt);
    }

    @Override
    public int getItemCount() {

        return matchPointGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView txt_datt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_datt=(TextView)itemView.findViewById(R.id.txt_datt);
        }
    }
}
