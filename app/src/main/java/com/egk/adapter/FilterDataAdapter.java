package com.egk.adapter;

import android.content.Context;
import androidx.annotation.NonNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egk.egk.R;
import com.egk.fragment.PreviousTestPAper_Fragment;
import com.egk.gettersetter.Filterdatagetset;
import com.egk.gettersetter.TestPeperGetterSetter;

import java.util.ArrayList;

public class FilterDataAdapter   extends RecyclerView.Adapter<FilterDataAdapter.ProgramViewHolder> {
    private ArrayList<Filterdatagetset> maarylis;
    Context context;
    public  FilterDataAdapter(ArrayList<Filterdatagetset> maarylis, Context context) {
        this.maarylis = maarylis;
        this.context = context;
    }

    @NonNull
    @Override
    public FilterDataAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.filterdata,viewGroup,false);
        return new FilterDataAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterDataAdapter.ProgramViewHolder programViewHolder, int i) {
        final Filterdatagetset My_list =maarylis.get(i);
        programViewHolder.not_txt.setText(removeHtml(My_list.getTestPaperTitle()));
    }


    @Override
    public int getItemCount() {
        return maarylis.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView not_txt;
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
        return html;
    }
}
