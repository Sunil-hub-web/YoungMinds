package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.egk.activites.GkCategory;
import com.egk.egk.R;
import com.egk.egk.Recy_recy_items;
import com.egk.extra.AppSingleton;

public class Gk_adapter extends RecyclerView.Adapter<Gk_adapter.ProgramViewHolder> {
    private ArrayList<Recy_recy_items> maarylis;
    Context context;
//    ImageLoader imageLoader = AppSingleton.getInstance(context).getImageLoader();
    public  Gk_adapter(ArrayList<Recy_recy_items> maarylis,Context context) {
        this.maarylis = maarylis;
        this.context = context;
    }
    @NonNull
    @Override
    public Gk_adapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.gk_rcy_item,parent,false);
        return new Gk_adapter.ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Gk_adapter.ProgramViewHolder holder, int Position) {
        final Recy_recy_items My_list =maarylis.get(Position);

        holder.conetwotxt.setText(removeHtml(My_list.getCategroyName()));
//        holder.conetwoimg.setImageUrl(My_list.getCategory_icon(), imageLoader);
        try {
            Glide.with(context).load(My_list.getCategory_icon()).into(holder.conetwoimg);
        }catch (Exception e){
            Log.d("wedfd", String.valueOf(e));
        }
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
        NetworkImageView coneimg;
                ImageView conetwoimg;
        TextView gktxt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            conetwotxt=(TextView)itemView.findViewById(R.id.conetwotxt);

            conetwoimg=(ImageView)itemView.findViewById(R.id.conetwoimg);
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

        return html;
    }
}


