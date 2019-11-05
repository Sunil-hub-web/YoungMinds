package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.MontlyGetSet;
import com.egk.gettersetter.ReportGetSet;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder>  {
    private ArrayList<ReportGetSet> reportGetSets;
    Context context;
    public  ReportAdapter(ArrayList<ReportGetSet> reportGetSets, Context context) {
        this.reportGetSets = reportGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.reportlayout,viewGroup,false);
        return new ReportAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder myViewHolder, int i) {
        final ReportGetSet My_list =reportGetSets.get(i);
        myViewHolder.txt_report.setText(My_list.getReports_name());
    }

    @Override
    public int getItemCount() {

        return reportGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_report;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_report=(TextView)itemView.findViewById(R.id.txt_report);
        }
    }
}
