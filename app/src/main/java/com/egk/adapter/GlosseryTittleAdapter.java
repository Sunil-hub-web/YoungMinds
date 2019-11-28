package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.gettersetter.Glosserytittlegetset;
import com.egk.gettersetter.MatchPointGetSet;

import java.util.ArrayList;

public class GlosseryTittleAdapter  extends RecyclerView.Adapter<GlosseryTittleAdapter.MyViewHolder> {

    private ArrayList<Glosserytittlegetset> glosserytittle;
    Context context;
    public  GlosseryTittleAdapter(ArrayList<Glosserytittlegetset> glosserytittle, Context context) {
        this.glosserytittle = glosserytittle;
        this.context = context;
    }
    @NonNull
    @Override
    public GlosseryTittleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.glosserytittleitem_layout,viewGroup,false);
        return new GlosseryTittleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlosseryTittleAdapter.MyViewHolder myViewHolder, int i) {
        final Glosserytittlegetset My_list =glosserytittle.get(i);
        myViewHolder.txt_gloss_tittle.setText(removeHtml(My_list.getTitle()));
        myViewHolder.desc.setText(removeHtml(My_list.getDecription()));
        Log.d("kdl",My_list.getTitle());
    }


    @Override
    public int getItemCount() {

        return glosserytittle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
     TextView txt_gloss_tittle,desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_gloss_tittle=(TextView)itemView.findViewById(R.id.txt_gloss_tittle);
            desc=(TextView)itemView.findViewById(R.id.desc);
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
        html = html.replaceAll("<p>","");

        return html;
    }
}
