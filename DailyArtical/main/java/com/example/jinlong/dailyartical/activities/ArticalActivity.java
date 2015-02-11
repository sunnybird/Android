package com.example.jinlong.dailyartical.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jinlong.dailyartical.R;
import com.example.jinlong.dailyartical.adapter.MyFragmentAdapter;
import com.example.jinlong.dailyartical.bean.Comment;
import com.example.jinlong.dailyartical.config.Config;
import com.example.jinlong.dailyartical.fragments.ArticalFragment;
import com.example.jinlong.dailyartical.fragments.CommentFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.UpdateListener;

public class ArticalActivity extends FragmentActivity {


    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager mViewPager;

    private List<Fragment> listofFragment;

    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);

        Bmob.initialize(this, Config.bmobAppKey);

        listofFragment = new ArrayList<Fragment>();

        listofFragment.add(new ArticalFragment());
        listofFragment.add(new CommentFragment());

        fragmentPagerAdapter = new MyFragmentAdapter(getSupportFragmentManager(),listofFragment);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(fragmentPagerAdapter);

    }

    /**
     * 返回键事件监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点赞事件监听
     *
     * @param view
     */

    public void startAdd(View view) {

        Bundle bundle = (Bundle) view.getTag();
        final String objectid = bundle.getString("obidetid");
        int number = bundle.getInt("number");
        Comment updatecomm = new Comment();
        updatecomm.setFavour(number + 1);
        updatecomm.update(this, objectid, new UpdateListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                //((CommentFragment) listOfFragment.get(0)).loadDate();
                ((CommentFragment) listofFragment.get(1)).updateComment(objectid);
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Log.i("err", "更新失败：" + msg);
            }
        });


    }



}
