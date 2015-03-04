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

    private List<Comment> comments;
    private LayoutInflater inflater;
    private Context context ;

    public ListViewAdapter(Context context, List<Comment> comments) {
        context = context;
        inflater = LayoutInflater.from(mContext);
        this.comments = comments;

    }
	
   @Override
    public boolean isEnabled(int position) {
      
        return false;
    }
    @Override
    public int getCount() {

        return comments.size();
    }

    @Override
    public Object getItem(int index) {
      
        return comments.get(index);
    }

    @Override
    public long getItemId(int arg0) {
     
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView contentTextView;     
        TextView timeTextView;
        ImageView ImageviewAgree;
        TextView numberofStartTextView;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.listview_item, null);
        }
		
        contentTextView = (TextView) convertView.findViewById(R.id.comment);       
        timeTextView = (TextView) convertView.findViewById(R.id.time);
        ImageviewAgree = (ImageView) convertView.findViewById(R.id.start_imageview);
        numberofStartTextView = (TextView) convertView.findViewById(R.id.numberofstart);
				
        contentTextView.setText(listOfComment.get(position).getContent());
        timeTextView.setText(listOfComment.get(position).getTime());
        numberofStartTextView.setText(listOfComment.get(position).getFavour()+"");
		
        Bundle bundle = new Bundle();
        bundle.putCharSequence("obidetid", listOfComment.get(position).getObjectId());
        bundle.putInt("number",listOfComment.get(position).getFavour());
        ImageviewAgree.setTag(bundle);
		
        return convertView;
    }

}