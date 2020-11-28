package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.egk.Gk_list;
import com.egk.egk.R;
import com.egk.gettersetter.GkList_getter_setter;

public class GklistAdapter  extends RecyclerView.Adapter<GklistAdapter.ProgramViewHolder> {
    private ArrayList<GkList_getter_setter> maarylis;
    Context context;
    public  GklistAdapter(ArrayList<GkList_getter_setter> maarylis, Gk_list my_gk) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public GklistAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new GklistAdapter.ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GklistAdapter.ProgramViewHolder holder, int Position) {
        final GkList_getter_setter My_list =maarylis.get(Position);
        holder.gktxt.setText(My_list.getGk_title());

//        holder.coneimg.setImageDrawable(My_list.Imgone);
        //   holder.coneimg.setImageResource(My_list.getImgone());
//        holder.conetwotxt.setText(My_list.Transactions);
//        holder.coneimg.setText(My_list.Imgone);
//        holder.conetwoimg.setText(My_list.Imgtwo);
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
        // ImageView coneimg,conetwoimg;
        TextView gktxt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            gktxt=(TextView)itemView.findViewById(R.id.textView);
//            conetwotxt=(TextView)itemView.findViewById(R.id.conetwotxt);
            // coneimg=(ImageView)itemView.findViewById(R.id.coneimg);
//            conetwoimg=(ImageView)itemView.findViewById(R.id.conetwoimg);
        }
    }

}


