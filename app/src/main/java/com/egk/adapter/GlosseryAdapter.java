package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.GlosseryGetSet;
import com.egk.gettersetter.ReportGetSet;

import java.util.ArrayList;

public class GlosseryAdapter extends RecyclerView.Adapter<GlosseryAdapter.MyViewHolder>  {
    private ArrayList<GlosseryGetSet> glosseryGetSets;
    Context context;
    public  GlosseryAdapter(ArrayList<GlosseryGetSet> glosseryGetSets, Context context) {
        this.glosseryGetSets = glosseryGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public GlosseryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.glossery_layout,viewGroup,false);
        return new GlosseryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlosseryAdapter.MyViewHolder myViewHolder, int i) {
        final GlosseryGetSet My_list =glosseryGetSets.get(i);
        myViewHolder.txt_item.setText(My_list.getName());
    }

    @Override
    public int getItemCount() {

        return glosseryGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item=(TextView)itemView.findViewById(R.id.txt_item);
        }
    }
}
