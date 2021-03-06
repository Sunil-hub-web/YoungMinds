package com.egk.egk;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ProgramViewHolder> {
    private ArrayList<Recy_list_items> maarylis;
    Context context;
    public  Home_Adapter(ArrayList<Recy_list_items> maarylis, Egk_nav egk_nav) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public Home_Adapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rcy_items,parent,false);
        return new ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Home_Adapter.ProgramViewHolder holder, int Position) {
        final Recy_list_items My_list =maarylis.get(Position);
        holder.conetxt.setText(My_list.Gk);

        holder.coneimg.setBackgroundResource(My_list.getImgone());

    }

    @Override
    public int getItemCount() {

        return maarylis.size();
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
    ImageView coneimg,conetwoimg;
    TextView conetxt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            conetxt=(TextView)itemView.findViewById(R.id.conetxt);
            coneimg=(ImageView)itemView.findViewById(R.id.coneimg);

        }
    }

}
