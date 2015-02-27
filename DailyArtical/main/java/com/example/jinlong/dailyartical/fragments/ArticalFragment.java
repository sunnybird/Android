package com.example.jinlong.dailyartical.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.bean.Artical;
import com.example.jinlong.dailyartical.config.Config;
import com.example.jinlong.dailyartical.networktask.HttpAsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;

/**
 * Created by JinLong on 2015/2/8.
 * 文章显示碎片
 */
public class ArticalFragment  extends Fragment {

    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewContent;
    private ImageView shareImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artical, container, false);
        textViewTitle = (TextView) view.findViewById(R.id.title);
        textViewAuthor = (TextView) view.findViewById(R.id.author);
        textViewContent = (TextView) view.findViewById(R.id.content);
        shareImageView = (ImageView) view.findViewById(R.id.ShareIV);
        shareImageView.setVisibility(View.GONE);

        loadData();

        return view;
    }

    /**
     * 下线文章
     */
    private void loadData() {
        File cacheDir = this.getActivity().getCacheDir();
        File acticalFile = new File(cacheDir, Config.LOCALFILENAME);
        try {

            FileInputStream inputStream = new FileInputStream(acticalFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Artical artical = (Artical) objectInputStream.readObject();

            textViewTitle.setText(artical.getTitle().replace("<h1>", "").replace("</h1>", ""));
            textViewAuthor.setText(artical.getAuthor().replace("<span>", "").replace("</span>", ""));
            textViewContent.setText(artical.getContent().replace("<p>", "").replace("</p>", "\n"));
        } catch (Exception e) {

            Log.i("file read", e.getMessage());

        }

    }


}


