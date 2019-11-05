package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.egk.R;
import com.egk.fragment.My_Topup;
import com.egk.gettersetter.Topup_geterseter;

public class TopupAdapter extends  RecyclerView.Adapter<TopupAdapter.ProgramViewHolder> {


    private ArrayList<Topup_geterseter> topuplist;
    Context context;


    public  TopupAdapter(ArrayList<Topup_geterseter> topuplist, My_Topup my_topup) {
        this.topuplist = topuplist;
        }
            @NonNull
            @Override
            public TopupAdapter.ProgramViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup,
            int i){
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                View view=inflater.inflate(R.layout.packageitem,viewGroup,false);
                return new TopupAdapter.ProgramViewHolder(view);
        }

            @Override
            public void onBindViewHolder (@NonNull TopupAdapter.ProgramViewHolder programViewHolder,
            int i){
                final Topup_geterseter My_list =topuplist.get(i);
                programViewHolder.packname.setText(My_list.getPackname());
                programViewHolder.txt_daysnumber.setText(My_list.getPackDays()+" days");
                programViewHolder.ruppes_value.setText("Rs. "+My_list.getPackCost());

        }
    @Override
    public int getItemCount() {

        return topuplist.size();
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

            public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView packname,txt_daysnumber,ruppes_value;

                public ProgramViewHolder(@NonNull View itemView) {
                    super(itemView);

                    packname=(TextView)itemView.findViewById(R.id.packname);
                    txt_daysnumber=(TextView)itemView.findViewById(R.id.validity);
                    ruppes_value=(TextView)itemView.findViewById(R.id.amount);



                }
            }
        }