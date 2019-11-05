package com.egk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.egk.activites.LeaderBoard;
import com.egk.egk.R;
import com.egk.gettersetter.QuizGetSet;

public class QuizAdapter  extends RecyclerView.Adapter<QuizAdapter.ProgramViewHolder> {


    private ArrayList<QuizGetSet> quizlist;

    Context context;


    public  QuizAdapter(ArrayList<QuizGetSet> quizlist, LeaderBoard leaderBoard) {
        this.quizlist = quizlist;
    }
    @NonNull
    @Override
    public QuizAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int Position ) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.quiz_item,parent,false);
        return new QuizAdapter.ProgramViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int Position) {
        final QuizGetSet My_list =quizlist.get(Position);

        holder.txt_num.setText(My_list.Number);
        holder.txt_name.setText(My_list.Name);
        holder.txt_mark.setText(My_list.Mark);
    }

    @Override
    public int getItemCount() {

        return quizlist.size();
    }
    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {

        TextView txt_num,txt_name,txt_mark;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_num=(TextView)itemView.findViewById(R.id.txt_num);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_mark=(TextView)itemView.findViewById(R.id.txt_mark);



        }
    }

}