package com.example.jinlong.dailyartical.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.adapter.ListViewAdapter;
import com.example.jinlong.dailyartical.bean.Comment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by JinLong on 2015/2/8.
 * 文章评论碎片
 */
public class CommentFragment extends Fragment implements View.OnClickListener {

    private List<Comment> listOfComment;
    private ListViewAdapter adapter;
    private Context context;
    private ListView listview;
    private EditText et;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, null);

        listview = (ListView) view.findViewById(R.id.commentlistview);
        et = (EditText) view.findViewById(R.id.comment_et);
        button = (Button) view.findViewById(R.id.sub_but);


        et.setOnClickListener(this);
        button.setOnClickListener(this);
        listview.setVerticalScrollBarEnabled(true);

        context = getActivity();

        listOfComment = new ArrayList<Comment>();
        adapter = new ListViewAdapter(context, listOfComment);

        listview.setAdapter(adapter);

        loadDate();
        return view;
    }

    /**
     * 下载历时评论
     */
    public void loadDate() {
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String where = format.format(d);
        BmobQuery<Comment> bmobQuery = new BmobQuery<Comment>();
        bmobQuery.setLimit(100);
        bmobQuery.order("-time");
        bmobQuery.addWhereStartsWith("time", where);
        bmobQuery.findObjects(context, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> objects) {

                Log.v("查询成功：共数据=", objects.size() + "");
                for (Comment comment : objects) {
                    listOfComment.add(comment);
                    Log.i("Objectid =", comment.getObjectId());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int code, String msg) {
                Log.v("err=", msg);
            }
        });

    }

    /**
     * 发表评论
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.comment_et:

                clearEditText();

                break;
            case R.id.sub_but:

                String msg = et.getText().toString().trim();
                if (!msg.equals("")) {

                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    final Comment comment = new Comment(msg, "User", format.format(date),
                            0);
                    comment.save(getActivity(), new SaveListener() {
                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                            toast("评论已经发表成功");
                            clearEditText();
                            //getData();
                            listOfComment.add(comment);
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onFailure(int code, String msg) {
                            // TODO Auto-generated method stub
                            toast("服务器出小差，请重试");
                        }
                    });

                }

                break;
            default:

                break;
        }

    }

    /**
     * 发表评论结果反馈
     * @param msg
     */
    private void toast(String msg) {

        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 初始化编辑框
     */
    private void clearEditText() {

        et.setText("");

    }

    public void updateComment(String objectid) {

        for (Comment mcomm : listOfComment) {
            if (mcomm.getObjectId().equals(objectid)) {
				
                mcomm.setFavour(mcomm.getFavour() + 1);

                break;
            }
        }
		
        adapter.notifyDataSetChanged();
    }


}

