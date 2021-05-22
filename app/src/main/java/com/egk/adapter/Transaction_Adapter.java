package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.egk.egk.R;
import com.egk.egk.Transaction_list_item;
import com.egk.fragment.My_Transaction;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.ProgramViewHolder> {
    private ArrayList<Transaction_list_item> maarylis;
    Context context;
    public  Transaction_Adapter(ArrayList<Transaction_list_item> maarylis, My_Transaction my_transaction) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public Transaction_Adapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.transaction_rcy_item,parent,false);
        return new Transaction_Adapter.ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Transaction_Adapter.ProgramViewHolder holder, int Position) {
        final Transaction_list_item My_list =maarylis.get(Position);
        holder.transaction_txt.setText(My_list.getTransaction_id());

        holder.txt_status.setText(My_list.getTransaction_type());
        holder.txt_price.setText("Rs. "+My_list.getTransaction_amount());
        holder.packagename.setText(My_list.getPackage_id());

        try {
            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            DateFormat targetFormat = new SimpleDateFormat("d MMM yyyy h:MM aa");
            Date date = null;
            date = originalFormat.parse(My_list.getTransaction_datetime());
            String formattedDate = targetFormat.format(date);
            holder.txt_time.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        if(My_list.getTransaction_status().equalsIgnoreCase("Success")){
//            holder.txt_status.setTextColor(Color.parseColor("#00F800"));
//        }
//        else if(My_list.getTransaction_status().equalsIgnoreCase("Failed")){
//            holder.txt_status.setTextColor(Color.parseColor("#F70000"));
//        }

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
        TextView transaction_txt,txt_time, txt_price, txt_status,packagename;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            transaction_txt=(TextView)itemView.findViewById(R.id.transaction_txt);
            txt_time=(TextView)itemView.findViewById(R.id.txt_time);
            txt_price=(TextView)itemView.findViewById(R.id.txt_price);
            txt_status=(TextView)itemView.findViewById(R.id.mode);
            packagename=(TextView)itemView.findViewById(R.id.packagename);

        }
    }

}

