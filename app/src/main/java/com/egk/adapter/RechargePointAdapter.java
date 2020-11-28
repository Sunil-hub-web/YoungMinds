package com.egk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.RechargePointSetget;

import java.util.ArrayList;

public class RechargePointAdapter extends RecyclerView.Adapter<RechargePointAdapter.MyViewHolder>  {
    private ArrayList<RechargePointSetget> reportGetSets;
    Context context;
    public  RechargePointAdapter(ArrayList<RechargePointSetget> reportGetSets, Context context) {
        this.reportGetSets = reportGetSets;
        this.context = context;
    }
    @NonNull
    @Override
    public RechargePointAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.rechargeagentlist,viewGroup,false);
        return new RechargePointAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RechargePointAdapter.MyViewHolder myViewHolder, int i) {
        final RechargePointSetget My_list =reportGetSets.get(i);
        myViewHolder.name.setText(My_list.getName());
        myViewHolder.email.setText(My_list.getEmail());
        myViewHolder.mobile.setText(My_list.getMobile());
        myViewHolder.callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + My_list.getMobile()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return reportGetSets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,mobile;
        Button callnow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            mobile=(TextView)itemView.findViewById(R.id.mobile);
            callnow =itemView.findViewById(R.id.callnow);

        }
    }
}
