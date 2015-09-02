package com.example.davidwang.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by DavidWang on 15/9/1.
 */
public class GridAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> data;

    public GridAdapter(Context context,ArrayList<String> data){
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.grid_item, null);
            holder.showimg = (ImageView)convertView.findViewById(R.id.showimg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }


        return convertView;
    }

    //ViewHolder静态类
    private class ViewHolder
    {
        public ImageView showimg;
    }
}
