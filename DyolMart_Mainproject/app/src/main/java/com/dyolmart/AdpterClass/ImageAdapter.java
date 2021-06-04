package com.dyolmart.AdpterClass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private String[] imageurl;

    public ImageAdapter(Context context, String[] imageurl) {
        this.context = context;
        this.imageurl = imageurl;
    }



    @Override
    public int getCount() {
        return imageurl.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        Picasso.get().load(imageurl[position]).fit().centerCrop().into(imageView);
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
