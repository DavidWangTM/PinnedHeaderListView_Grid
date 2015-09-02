package com.example.davidwang.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidwang.demo.Model.MainDetailInfo;
import com.example.davidwang.demo.Model.MainInfo;

import java.util.ArrayList;

/**
 * Created by DavidWang on 15/9/1.
 */
public class MainAdapter extends SectionedBaseAdapter {

    private ArrayList<MainInfo> data;
    private Context context;

    private int s_section,s_position = -1;


    public MainAdapter(Context context,ArrayList<MainInfo> data){
        this.data = data;
        this.context = context;
    }


    public Object getItem(int section, int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSectionCount() {//header的数量
        return data.size();
    }

    @Override
    public int getCountForSection(int section) {//子item的数量
        return data.get(section).data.size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {//普通item的getview方法
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.list_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        MainInfo info = data.get(section);
        MainDetailInfo dinfo = info.data.get(position);
        TextView textView = ((TextView) layout.findViewById(R.id.contentText));
        textView.setText(dinfo.name);
        textView.setOnClickListener(new textListener(section,position));


        GridView gridview = (GridView)layout.findViewById(R.id.grid);
        ArrayList<String> sdata = dinfo.url;
        if (dinfo.is_open){
            GridAdapter adapter = new GridAdapter(context,sdata);
            gridview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            int size = sdata.size();
            int a = size/2;
            int b = size%2;
            if (b > 0) {
                a++;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(720,a*220);
            gridview.setLayoutParams(layoutParams);
            gridview.setVisibility(View.VISIBLE);
        }else{
            gridview.setVisibility(View.GONE);
        }




        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {//header的getview方法
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }

        MainInfo info = data.get(section);
        ((TextView) layout.findViewById(R.id.headText)).setText(info.name);
        return layout;
    }

    class textListener implements View.OnClickListener{

        private int section,position;

        private textListener(int section ,int position){
            this.section = section;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (s_position == -1){
                s_position = position;
                s_section = section;
            }else{
                MainInfo info = data.get(s_section);
                ArrayList<MainDetailInfo> idata = info.data;
                MainDetailInfo dinfo = idata.get(s_position);
                dinfo.is_open = !dinfo.is_open;
                idata.set(s_position,dinfo);
                data.set(s_section,info);
            }
            s_position = position;
            s_section = section;
            MainInfo info = data.get(section);
            ArrayList<MainDetailInfo> idata = info.data;
            MainDetailInfo dinfo = idata.get(position);
            dinfo.is_open = !dinfo.is_open;
            idata.set(position,dinfo);
            data.set(section,info);
            notifyDataSetChanged();


        }
    }


}




