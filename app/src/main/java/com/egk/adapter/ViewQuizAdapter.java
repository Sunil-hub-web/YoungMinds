package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.activites.ViewQuiz;
import com.egk.egk.R;
import com.egk.gettersetter.ViewQuizGetSet;

public class ViewQuizAdapter extends RecyclerView.Adapter<ViewQuizAdapter.ProgramViewHolder> {

    private ArrayList<ViewQuizGetSet> viewquiz;
    Context context;


    public  ViewQuizAdapter(ArrayList<ViewQuizGetSet> viewquiz, ViewQuiz viewQuiz) {
        this.viewquiz = viewquiz;
    }

    @NonNull
    @Override
    public ViewQuizAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.viewquiz,viewGroup,false);
        return new ViewQuizAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewQuizAdapter.ProgramViewHolder holder, int i) {
        final ViewQuizGetSet My_list =viewquiz.get(i);
        holder.txt_quizname.setText(My_list.Ses_name);
        holder.txt_quizdate.setText(My_list.StartDate);
        holder.txt_quiztime.setText(My_list.StartTime);

    }

    @Override
    public int getItemCount() {

        return viewquiz.size();
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }


    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_quizname,txt_date,txt_quizdate,txt_time,txt_quiztime;


        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_quizname=(TextView)itemView.findViewById(R.id.txt_quizname);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            txt_quizdate=(TextView)itemView.findViewById(R.id.txt_quizdate);
            txt_time=(TextView)itemView.findViewById(R.id.txt_time);
            txt_quiztime=(TextView)itemView.findViewById(R.id.txt_quiztime);

        }
    }
}
