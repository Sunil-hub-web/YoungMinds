package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.MatchPointGetSet;
import com.egk.gettersetter.MontlyGetSet;

import java.util.ArrayList;

public class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.MyViewHolder>  {
    private ArrayList<MontlyGetSet> montlyGetSets;
    Context context;
    public  MonthlyAdapter(ArrayList<MontlyGetSet> montlyGetSets, Context context) {
        this.montlyGetSets = montlyGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public MonthlyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.monthlygk_layout,viewGroup,false);
        return new MonthlyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyAdapter.MyViewHolder myViewHolder, int i) {
        final MontlyGetSet My_list =montlyGetSets.get(i);
        myViewHolder.txt_month.setText(My_list.getMonthly_gk_date());
    }

    @Override
    public int getItemCount() {

        return montlyGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_month;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_month=(TextView)itemView.findViewById(R.id.txt_month);

        }
    }
}
