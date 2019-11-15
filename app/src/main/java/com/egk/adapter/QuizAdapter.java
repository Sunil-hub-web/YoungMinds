package com.egk.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.egk.activites.LeaderBoard;
import com.egk.egk.R;
import com.egk.fragment.MyProfile;
import com.egk.gettersetter.QuizGetSet;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class QuizAdapter  extends RecyclerView.Adapter<QuizAdapter.ProgramViewHolder> {


    private ArrayList<QuizGetSet> quizlist;

    Context context;


    public  QuizAdapter(ArrayList<QuizGetSet> quizlist,Context context) {
        this.quizlist = quizlist;
        this.context = context;
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
        if (My_list.getImg().equalsIgnoreCase("")){
            Picasso.with(context).load(R.drawable.profile_icon).transform(new CircleTransform()).into(holder.img);
        }else {
            Picasso.with(context).load(My_list.getImg()).transform(new CircleTransform()).into(holder.img);
        }
        holder.txt_num.setText(My_list.Number);
        holder.txt_name.setText(My_list.Name);
        holder.txt_mark.setText(My_list.Mark);
//
//        if (My_list.getImg().equals("")) {
//            Glide.with(context).load(R.drawable.profile_icon).into(holder.img);
//        } else {
//            try {
//                Glide.with(context).load(My_list.getImg()).into(holder.img);
//            } catch (Exception e) {
//                Log.d("wedfd", String.valueOf(e));
//
//            }
//    }
    }

    @Override
    public int getItemCount() {

        return quizlist.size();
    }


    public class ProgramViewHolder extends RecyclerView.ViewHolder {

        TextView txt_num,txt_name,txt_mark;
        ImageView img;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_num=(TextView)itemView.findViewById(R.id.txt_num);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_mark=(TextView)itemView.findViewById(R.id.txt_mark);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }
    class CircleTransform implements Transformation {

        boolean mCircleSeparator = false;

        public CircleTransform() {
        }

        public CircleTransform(boolean circleSeparator) {
            mCircleSeparator = circleSeparator;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
            Canvas canvas = new Canvas(bitmap);
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
            paint.setShader(shader);
            float r = size / 2f;
            canvas.drawCircle(r, r, r - 1, paint);
            // Make the thin border:
            Paint paintBorder = new Paint();
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setColor(Color.argb(84,0,0,0));
            paintBorder.setAntiAlias(true);
            paintBorder.setStrokeWidth(1);
            canvas.drawCircle(r, r, r-1, paintBorder);

            // Optional separator for stacking:
            if (mCircleSeparator) {
                Paint paintBorderSeparator = new Paint();
                paintBorderSeparator.setStyle(Paint.Style.STROKE);
                paintBorderSeparator.setColor(Color.parseColor("#ffffff"));
                paintBorderSeparator.setAntiAlias(true);
                paintBorderSeparator.setStrokeWidth(4);
                canvas.drawCircle(r, r, r+1, paintBorderSeparator);
            }
            squaredBitmap.recycle();
            return bitmap;
        }


        @Override
        public String key() {
            return "circle";
        }
    }

}