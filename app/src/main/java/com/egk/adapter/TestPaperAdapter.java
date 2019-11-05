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
import com.egk.fragment.PreviousTestPAper_Fragment;
import com.egk.gettersetter.TestPeperGetterSetter;

public class TestPaperAdapter extends RecyclerView.Adapter<TestPaperAdapter.ProgramViewHolder> {
    private ArrayList<TestPeperGetterSetter> maarylis;
    Context context;
    public  TestPaperAdapter(ArrayList<TestPeperGetterSetter> maarylis, PreviousTestPAper_Fragment my_notifications) {
        this.maarylis = maarylis;
    }
    @NonNull
    @Override
    public TestPaperAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.notification_rcy_item,parent,false);
        return new TestPaperAdapter.ProgramViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TestPaperAdapter.ProgramViewHolder holder, int Position) {
        final TestPeperGetterSetter My_list =maarylis.get(Position);
        holder.not_txt.setText(removeHtml(My_list.getTestPaperTitle()));

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
        TextView not_txt,conetwotxt;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            not_txt=(TextView)itemView.findViewById(R.id.textView);
//
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
        return html;
    }
}


