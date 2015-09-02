package com.example.davidwang.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.davidwang.demo.Model.MainDetailInfo;
import com.example.davidwang.demo.Model.MainInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private PinnedHeaderListView listView;
    private MainAdapter adapter;

    private ArrayList<MainInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        inData();
    }


    private void findID(){
        listView = (PinnedHeaderListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
    }

    private void inData(){
        data = new ArrayList<MainInfo>();
        for (int i = 0;i< 5;i++){
            MainInfo info = new MainInfo();
            info.name = "我是标题"+i;
            ArrayList<MainDetailInfo> darr = new ArrayList<MainDetailInfo>();
            for (int j = 0 ;j < i + 1 ; j++){
                MainDetailInfo dinfo = new MainDetailInfo();
                dinfo.name = "我是内容"+j;
                dinfo.is_open = false;
                ArrayList<String> aarr = new ArrayList<String>();
                for (int a = 0; a < j + 1; a++){
                    aarr.add("");
                }
                dinfo.url = aarr;
                darr.add(dinfo);
            }
            info.data = darr;
            data.add(info);
        }
        adapter = new MainAdapter(MainActivity.this,data);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("1",position+"");
    }
}
