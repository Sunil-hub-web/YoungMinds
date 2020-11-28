package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.egk.Notification_recy_list;
import com.egk.egk.R;
import com.egk.fragment.My_Notifications;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ProgramViewHolder> {
    private ArrayList<Notification_recy_list> maarylis;
    Context context;
    public  Notification_Adapter(ArrayList<Notification_recy_list> maarylis, My_Notifications my_notifications) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public Notification_Adapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.notification_rcy_item,parent,false);
        return new Notification_Adapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Notification_Adapter.ProgramViewHolder holder, int Position) {
        final Notification_recy_list My_list =maarylis.get(Position);
        holder.not_txt.setText(removeHtml(My_list.getNotificationDesc()));
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

        TextView not_txt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            not_txt=(TextView)itemView.findViewById(R.id.textView);
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


