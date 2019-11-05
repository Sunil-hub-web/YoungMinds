package com.egk.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.egk.egk.R;
import com.egk.extra.AppSingleton;
import com.egk.gettersetter.SliderGetSet;

import java.util.List;

public class MyAdapter  extends PagerAdapter {


    //    private String[] urls;
    private List<SliderGetSet> urls;
    private LayoutInflater inflater;
    private Context context;
//    ImageLoader imageLoader = AppSingleton.getInstance(context).getImageLoader();


    public MyAdapter(Context context, List<SliderGetSet> urls) {
        this.context = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slide, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        SliderGetSet m = urls.get(position);

        Log.d("jssj", String.valueOf(m.getBannerimage()));

        Glide.with(context)
                .load(m.getBannerimage())
                .into(imageView);
//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);

//        String img = m.getBannerimage();
//        holder.thumbnail.setImageBitmap(getBitmapFromURL(img));
//        imageView.setImageUrl(img,imageLoader);

//        Picasso.get().load(img).into(holder.thumbnail);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}