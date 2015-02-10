package com.example.jinlong.dailyartical.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.bean.Comment;

import java.util.List;

/**
 * Created by JinLong on 2015/2/8.
 */
public class ListViewAdapter extends BaseAdapter {

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return false;
    }

    private List<Comment> listOfComment;

    private LayoutInflater mInflater;
    private Context mContext = null;

    public ListViewAdapter(Context context, List<Comment> listOfCotent) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.listOfComment = listOfCotent;

    }

    @Override
    public int getCount() {

        return listOfComment.size();
    }

    @Override
    public Object getItem(int index) {
        // TODO Auto-generated method stub
        return listOfComment.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView contentTextView;
       // TextView userTextView;
        TextView timeTextView;
        ImageView ImageviewAgree;
        TextView numberofStartTextView;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.listview_item, null);
        }
        contentTextView = (TextView) convertView.findViewById(R.id.comment);
        //userTextView = (TextView) convertView.findViewById(R.id.user);
        timeTextView = (TextView) convertView.findViewById(R.id.time);
        ImageviewAgree = (ImageView) convertView.findViewById(R.id.start_imageview);

        numberofStartTextView = (TextView) convertView
                .findViewById(R.id.numberofstart);

        contentTextView.setText(listOfComment.get(position).getContent());
       // userTextView.setText(listOfComment.get(position).getUser());
        timeTextView.setText(listOfComment.get(position).getTime());
        numberofStartTextView.setText(listOfComment.get(position).getFavour()+"");
        Bundle bundle = new Bundle();
        bundle.putCharSequence("obidetid", listOfComment.get(position).getObjectId());
        bundle.putInt("number",listOfComment.get(position).getFavour());
        ImageviewAgree.setTag(bundle);


        return convertView;

    }

}