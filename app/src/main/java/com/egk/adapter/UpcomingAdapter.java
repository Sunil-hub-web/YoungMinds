package com.egk.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.UpcomingExam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder>  {
    private ArrayList<UpcomingExam> matchPointGetSets;
    Context context;
    public  UpcomingAdapter(ArrayList<UpcomingExam> matchPointGetSets, Context context) {
        this.matchPointGetSets = matchPointGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public UpcomingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.upcoming_exam_irm,viewGroup,false);
        return new UpcomingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.MyViewHolder myViewHolder, int i) {
        final UpcomingExam My_list =matchPointGetSets.get(i);

        String strCurrentDate = My_list.getDate();
        String description = My_list.getExamTitle();
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


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {

                        myViewHolder.txt_datt.setText("Date : " + Html.fromHtml(dt,Html.FROM_HTML_MODE_COMPACT));
            myViewHolder.Descriptions.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_COMPACT));
        }else{

            myViewHolder.txt_datt.setText("Date : " + Html.fromHtml(dt));
            myViewHolder.Descriptions.setText(Html.fromHtml(description));

        }
    }

    @Override
    public int getItemCount() {

        return matchPointGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_datt,Descriptions;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_datt=(TextView)itemView.findViewById(R.id.txt_datt);
            Descriptions=(TextView)itemView.findViewById(R.id.Description);


        }
    }
}
