package com.garagy.movieapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by The Maestro on 12/25/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String mUrl[];

    public ImageAdapter(Context c, String url[]) {
        mContext = c;
        mUrl = url;
    }

    @Override
    public int getCount() {
        return mUrl.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(mUrl[position]).into(imageView);
        return imageView;
    }
}
