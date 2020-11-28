package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.egk.Offer_recy_list;
import com.egk.egk.R;
import com.egk.fragment.My_Offers;

public class Offer_Adapter extends RecyclerView.Adapter<Offer_Adapter.ProgramViewHolder> {
    private ArrayList<Offer_recy_list> maarylis;
    Context context;
    public  Offer_Adapter(ArrayList<Offer_recy_list> maarylis, My_Offers my_offers) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public Offer_Adapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.offers_rcy_item,parent,false);
        return new Offer_Adapter.ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Offer_Adapter.ProgramViewHolder holder, int Position) {
        final Offer_recy_list My_list =maarylis.get(Position);
        holder.offer_txt.setText(removeHtml(My_list.getOfferDDesc()));

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
        TextView offer_txt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            offer_txt=(TextView)itemView.findViewById(R.id.textView);
//            conetwotxt=(TextView)itemView.findViewById(R.id.conetwotxt);
            // coneimg=(ImageView)itemView.findViewById(R.id.coneimg);
//            conetwoimg=(ImageView)itemView.findViewById(R.id.conetwoimg);
        }
    }
    private String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>"," ");
        html = html.replaceAll("<(.*?)\\\n"," ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&#039;s"," ");
        html = html.replaceAll("&amp;","&");
        html = html.replaceAll("&#39;","'");
        html = html.replaceAll("&nbsp;","");
        html = html.replaceAll("&amp;"," & ");
        html = html.replaceAll("&nbs","");
        html = html.replaceAll("&am"," ");
        html = html.replaceAll("&rsquo;","");
        html = html.replaceAll("&lsquo;","");
        html = html.replaceAll("&ldquo;","");
        html = html.replaceAll("&rdquo;","");
        html = html.replaceAll("&ndash;","");
        html = html.replaceAll("&rsquo;s","");
        html = html.replaceAll("&hellip;","");
        html = html.replaceAll("&#039;"," ");
        html = html.replaceAll("&quot;"," ");


        return html;
    }

}
