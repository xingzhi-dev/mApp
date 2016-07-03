package com.example.xiedongdong.app02.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiedongdong.app02.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xiedongdong on 16/6/29.
 */
public class NewsListViewAdapter extends BaseAdapter {

    public static final String KEY_TITLE="title";   //标题
    public static final String KEY_FROM="fromUrl";   //文章来自哪里
    public static final String KEY_HEADIMG="headImg"; //用户头像
    public static final String KEY_TIME="time";   //时间
    public static final String KEY_TITLEIMG="titleImg";  //文章配图
    public static final String KEY_URL="url";  //文章链接

    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<HashMap<String,String>> data;

    public NewsListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> data){
        this.activity=activity;
        this.data=data;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int postion) {
        return postion;
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        LinearLayout ll=null;
        if(convertView!=null){
            ll= (LinearLayout) convertView;
        }else{
            ll= (LinearLayout) inflater.inflate(R.layout.layout_community_item,null);
        }

        TextView tv_title= (TextView) ll.findViewById(R.id.tv_title); //标题
        TextView tv_fromUrl= (TextView) ll.findViewById(R.id.tv_fromUrl);//来自
        ImageView img_headImg= (ImageView) ll.findViewById(R.id.img_headImg);//用户头像
        TextView tv_time= (TextView) ll.findViewById(R.id.tv_time);//时间
        ImageView img_title= (ImageView) ll.findViewById(R.id.img_title);//标题头像

        HashMap<String,String> news=new HashMap<>();
        news=data.get(postion);

        //设置Listview相关的值

        tv_title.setText(news.get(KEY_TITLE));
        tv_fromUrl.setText(news.get(KEY_FROM));

        tv_time.setText(news.get(KEY_TIME));

        return ll;
    }
}
